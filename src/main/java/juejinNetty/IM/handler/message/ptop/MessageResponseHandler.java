package juejinNetty.IM.handler.message.ptop;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import juejinNetty.IM.packet.message.ptop.MessageResponsePacket;

/**
 * @Classname MessageResponseHandler
 * @Description
 * @Date 2022/11/25 20:12
 * @Created by brain
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket messageResponsePacket) {
//        System.out.println(LoginUtil.DATE_FORMAT.format(new Date()) + ": 收到服务端的消息: " + messageResponsePacket.getMessage());
        String fromUserId = messageResponsePacket.getFromUserId();
        String fromUserName = messageResponsePacket.getFromUserName();
        System.out.println("收到新消息:");
        System.out.print(fromUserId + ":" + fromUserName + " -> " + messageResponsePacket .getMessage());
        System.out.print("请输入接收方ID:");
    }

}
