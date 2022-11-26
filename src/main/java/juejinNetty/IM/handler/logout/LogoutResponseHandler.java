package juejinNetty.IM.handler.logout;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import juejinNetty.IM.packet.logout.LogoutResponsePacket;
import juejinNetty.IM.util.SessionUtil;

/**
 * @Classname LogoutResponseHandler
 * @Description
 * @Date 2022/11/26 13:33
 * @Created by brain
 */
public class LogoutResponseHandler extends SimpleChannelInboundHandler<LogoutResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutResponsePacket msg) throws Exception {
        SessionUtil.unBindSession(ctx.channel());
    }
}
