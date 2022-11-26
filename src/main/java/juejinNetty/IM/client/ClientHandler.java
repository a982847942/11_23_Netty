package juejinNetty.IM.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import juejinNetty.IM.packet.Packet;
import juejinNetty.IM.packet.PacketCodeC;
import juejinNetty.IM.packet.login.LoginRequestPacket;
import juejinNetty.IM.packet.login.LoginResponsePacket;
import juejinNetty.IM.packet.message.ptop.MessageResponsePacket;
import juejinNetty.IM.util.LoginUtil;

import java.util.Date;
import java.util.UUID;

/**
 * @Description
 * @Date 2022/11/23 23:04
 * @Created by brain
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(LoginUtil.DATE_FORMAT.format(new Date())+ ": 客户端开始登陆");
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUserName("flash");
        loginRequestPacket.setPassword("pwd");
//        ByteBuf buf = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginRequestPacket);
        ctx.channel().writeAndFlush(loginRequestPacket);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf)msg;
        Packet packet = PacketCodeC.INSTANCE.decode(buf);
        if (packet instanceof  LoginResponsePacket){
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;
            if (loginResponsePacket.isSuccess()){
                //设置标志位
                System.out.println(LoginUtil.DATE_FORMAT.format(new Date()) + ": 客户端登陆成功");
                LoginUtil.markAsLogin(ctx.channel());
            }else {
                System.out.println(LoginUtil.DATE_FORMAT.format(new Date()) + ": 客户端登陆失败! 原因：" + loginResponsePacket.getReason());
            }
        }else if (packet instanceof MessageResponsePacket) {
            MessageResponsePacket messageResponsePacket = (MessageResponsePacket) packet;
            System.out.println(LoginUtil.DATE_FORMAT.format(new Date()) + ": 收到服务端的消息: \n" + messageResponsePacket.getMessage());
        }
    }
}
