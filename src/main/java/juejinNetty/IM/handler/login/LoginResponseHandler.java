package juejinNetty.IM.handler.login;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import juejinNetty.IM.packet.login.LoginResponsePacket;
import juejinNetty.IM.session.Session;
import juejinNetty.IM.util.SessionUtil;

/**
 * @Classname LoginResponseHandler
 * @Description
 * @Date 2022/11/25 20:12
 * @Created by brain
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {
//    @Override
//    public void channelActive(ChannelHandlerContext ctx) {
//        // 创建登录对象
//        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
//        loginRequestPacket.setUserId(UUID.randomUUID().toString());
//        loginRequestPacket.setUserName("flash");
//        loginRequestPacket.setPassword("pwd");
//
//        // 写数据
//        ctx.channel().writeAndFlush(loginRequestPacket);
//    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginResponsePacket) {
        String userId = loginResponsePacket.getUserId();
        String userName = loginResponsePacket.getUserName();
        if (loginResponsePacket.isSuccess()) {
//            System.out.println(LoginUtil.DATE_FORMAT.format(new Date()) + ": 客户端登录成功");
            System.out.println("[" + userName + "]登录成功，userId 为: " + loginResponsePacket.getUserId());
            SessionUtil.bindSession(new Session(userId,userName),ctx.channel());
        } else {
//            System.out.println(LoginUtil.DATE_FORMAT.format(new Date())+ ": 客户端登录失败，原因：" + loginResponsePacket.getReason());
            System.out.println("[" + userName + "]登录失败，原因：" + loginResponsePacket.getReason());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println("客户端连接被关闭!");
    }
}
