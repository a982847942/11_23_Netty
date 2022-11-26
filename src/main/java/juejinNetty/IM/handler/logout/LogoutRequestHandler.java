package juejinNetty.IM.handler.logout;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import juejinNetty.IM.packet.logout.LogoutRequestPacket;
import juejinNetty.IM.packet.logout.LogoutResponsePacket;
import juejinNetty.IM.util.SessionUtil;

/**
 * @Classname LogoutRequestHandler
 * @Description
 * @Date 2022/11/26 13:29
 * @Created by brain
 */
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutRequestPacket msg) {
        SessionUtil.unBindSession(ctx.channel());
        LogoutResponsePacket logoutResponsePacket = new LogoutResponsePacket();
        logoutResponsePacket.setSuccess(true);
        ctx.channel().writeAndFlush(logoutResponsePacket);
    }
}
