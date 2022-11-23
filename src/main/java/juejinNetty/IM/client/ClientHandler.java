package juejinNetty.IM.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import juejinNetty.IM.message.Packet;
import juejinNetty.IM.message.PacketCodeC;
import juejinNetty.IM.message.login.LoginRequestPacket;
import juejinNetty.IM.message.login.LoginResponsePacket;
import juejinNetty.IM.util.LoginUtil;

import java.text.SimpleDateFormat;
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
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(new Date())
                + ": 客户端开始登陆");
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        //设置 userID 用户名 密码
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("flash");
        loginRequestPacket.setPassword("pwd");
        //序列化
        ByteBuf buf = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginRequestPacket);
        //发送
        ctx.channel().writeAndFlush(buf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf)msg;
        Packet packet = PacketCodeC.INSTANCE.decode(buf);
        if (packet instanceof LoginResponsePacket){
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;
            if (loginResponsePacket.isSuccess()){
                //设置标志位
                LoginUtil.markAsLogin(ctx.channel());
                System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(new Date())
                        + ": 客户端登陆成功");
            }else {
                System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(new Date())
                        + ": 客户端登陆失败! 原因：" + loginResponsePacket.getReason());
            }
        }
//        else if (packet instanceof MessageResponsePacket) {
//            MessageResponsePacket messageResponsePacket = (MessageResponsePacket) packet;
//            System.out.println(new Date() + ": 收到服务端的消息: " + messageResponsePacket.getMessage());
//        }
    }
}
