package juejinNetty.IM.message.login.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import juejinNetty.IM.message.PacketCodeC;
import juejinNetty.IM.message.login.LoginRequestPacket;
import juejinNetty.IM.message.login.LoginResponsePacket;
import juejinNetty.IM.util.LoginUtil;

import java.util.Date;

/**
 * @Classname LoginRequestHandler
 * @Description
 * @Date 2022/11/25 20:18
 * @Created by brain
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(msg.getVersion());
        //验证账号密码等
        if (valid(ctx,msg)){
            loginResponsePacket.setSuccess(true);
            System.out.println(LoginUtil.DATE_FORMAT.format(new Date()) + ": 登录成功!");
        }else {
            loginResponsePacket.setReason("账号密码校验失败");
            loginResponsePacket.setSuccess(false);
            System.out.println(LoginUtil.DATE_FORMAT.format(new Date()) + ": 登录失败!");
        }
//        ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginResponsePacket);
        ctx.channel().writeAndFlush(loginResponsePacket);
    }
    private boolean valid(ChannelHandlerContext ctx ,LoginRequestPacket loginRequestPacket) {
        LoginUtil.markAsLogin(ctx.channel());
        return true;
    }
}
