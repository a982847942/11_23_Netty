package juejinNetty.IM.handler.login;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import juejinNetty.IM.packet.login.LoginRequestPacket;
import juejinNetty.IM.packet.login.LoginResponsePacket;
import juejinNetty.IM.session.Session;
import juejinNetty.IM.util.LoginUtil;
import juejinNetty.IM.util.SessionUtil;

import java.util.Date;
import java.util.UUID;

/**
 * @Classname LoginRequestHandler
 * @Description
 * @Date 2022/11/25 20:18
 * @Created by brain
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) throws Exception {
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
        //验证账号密码等
        if (valid(ctx,loginRequestPacket)){
//            LoginUtil.markAsLogin(ctx.channel());
//            loginResponsePacket.setSuccess(true);
//            System.out.println(LoginUtil.DATE_FORMAT.format(new Date()) + ": 登录成功!");
            loginResponsePacket.setSuccess(true);
            String userId = randomUserId();
            loginResponsePacket.setUserId(userId);
            loginResponsePacket.setUserName(loginRequestPacket.getUserName());
            System.out.println("[" + loginRequestPacket.getUserName() + "]登录成功");
            SessionUtil.bindSession(new Session(userId, loginRequestPacket.getUserName()), ctx.channel());
        }else {
            loginResponsePacket.setReason("账号密码校验失败");
            loginResponsePacket.setSuccess(false);
            System.out.println(LoginUtil.DATE_FORMAT.format(new Date()) + ": 登录失败!");
        }
//        ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginResponsePacket);
        ctx.channel().writeAndFlush(loginResponsePacket);
    }
    private boolean valid(ChannelHandlerContext ctx ,LoginRequestPacket loginRequestPacket) {
        return true;
    }
    private static String randomUserId() {
        return UUID.randomUUID().toString().split("-")[0];
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        SessionUtil.unBindSession(ctx.channel());
    }
}
