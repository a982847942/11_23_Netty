package juejinNetty.IM.server;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import juejinNetty.IM.packet.Packet;
import juejinNetty.IM.packet.PacketCodeC;
import juejinNetty.IM.packet.login.LoginRequestPacket;
import juejinNetty.IM.packet.login.LoginResponsePacket;
import juejinNetty.IM.packet.message.ptop.MessageRequestPacket;
import juejinNetty.IM.packet.message.ptop.MessageResponsePacket;
import juejinNetty.IM.util.LoginUtil;

import java.util.Date;

/**
 * @Classname ServerHandler
 * @Description
 * @Created by brain
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(LoginUtil.DATE_FORMAT.format(new Date()) + ": 客户端开始登录....");
        ByteBuf buf = (ByteBuf) msg;
        Packet packet = PacketCodeC.INSTANCE.decode(buf);
        if (packet instanceof  LoginRequestPacket){
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;
            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            loginResponsePacket.setVersion(loginRequestPacket.getVersion());
            if (valid(loginRequestPacket)){
                loginResponsePacket.setSuccess(true);
                System.out.println(LoginUtil.DATE_FORMAT.format(new Date()) + ": 登录成功!");
            }else {
                loginResponsePacket.setReason("账号密码校验失败");
                loginResponsePacket.setSuccess(false);
                System.out.println(LoginUtil.DATE_FORMAT.format(new Date()) + ": 登录失败!");
            }
//            ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginResponsePacket);
            ctx.channel().writeAndFlush(loginResponsePacket);
        }else if (packet instanceof MessageRequestPacket){
            // 处理消息
            MessageRequestPacket messageRequestPacket = ((MessageRequestPacket) packet);
            System.out.println(LoginUtil.DATE_FORMAT.format(new Date()) + ": 收到客户端消息: \n" + messageRequestPacket.getMessage());

            MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
            messageResponsePacket.setMessage("服务端回复\n" + messageRequestPacket.getMessage());
//            ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), messageResponsePacket);
            ctx.channel().writeAndFlush(messageResponsePacket);
        }
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
