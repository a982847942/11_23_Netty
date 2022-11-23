package juejinNetty.IM.server;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import juejinNetty.IM.message.Packet;
import juejinNetty.IM.message.PacketCodeC;
import juejinNetty.IM.message.login.LoginRequestPacket;
import juejinNetty.IM.message.login.LoginResponsePacket;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Classname ServerHandler
 * @Description
 * @Created by brain
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(new Date())
        + ": 客户端开始登录....");
        ByteBuf buf = (ByteBuf) msg;
        //反序列化
        Packet packet = PacketCodeC.INSTANCE.decode(buf);
        if (packet instanceof LoginRequestPacket){
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;
            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            loginResponsePacket.setVersion(loginRequestPacket.getVersion());
            if (valid(loginRequestPacket)){
                loginResponsePacket.setSuccess(true);
                System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ": 登录成功!");
            }else {
                loginResponsePacket.setReason("账号密码校验失败");
                loginResponsePacket.setSuccess(false);
                System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ": 登录失败!");
            }
            //回复登陆成功消息
            ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginResponsePacket);
            ctx.channel().writeAndFlush(byteBuf);
        }
//        else if (packet instanceof MessageRequestPacket){
//            // 处理消息
//            MessageRequestPacket messageRequestPacket = ((MessageRequestPacket) packet);
//            System.out.println(new Date() + ": 收到客户端消息: " + messageRequestPacket.getMessage());
//
//            MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
//            messageResponsePacket.setMessage("服务端回复【" + messageRequestPacket.getMessage() + "】");
//            ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), messageResponsePacket);
//            ctx.channel().writeAndFlush(responseByteBuf);
//        }
    }

    //验证
    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
