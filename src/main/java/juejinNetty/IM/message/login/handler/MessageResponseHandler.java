package juejinNetty.IM.message.login.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import juejinNetty.IM.message.login.MessageResponsePacket;
import juejinNetty.IM.util.LoginUtil;

import java.util.Date;

/**
 * @Classname MessageResponseHandler
 * @Description
 * @Date 2022/11/25 20:12
 * @Created by brain
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket messageResponsePacket) {
        System.out.println(LoginUtil.DATE_FORMAT.format(new Date()) + ": 收到服务端的消息: " + messageResponsePacket.getMessage());
    }
}
