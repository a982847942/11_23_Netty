package juejinNetty.IM.handler.message.ptop;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import juejinNetty.IM.packet.message.ptop.MessageRequestPacket;
import juejinNetty.IM.packet.message.ptop.MessageResponsePacket;
import juejinNetty.IM.session.Session;
import juejinNetty.IM.util.SessionUtil;

/**
 * @Classname MessageRequestHandler
 * @Description
 * @Date 2022/11/25 20:25
 * @Created by brain
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
//    @Override
//    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) throws Exception {
//        // 处理消息
//        System.out.println(LoginUtil.DATE_FORMAT.format(new Date()) + ": 收到客户端消息: \n" + msg.getMessage());
//
//        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
//        messageResponsePacket.setMessage("服务端回复\n" + msg.getMessage());
////        ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), messageResponsePacket);
//        ctx.channel().writeAndFlush(messageResponsePacket);
//    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) {
        // 1.拿到消息发送方的会话信息
        Session session = SessionUtil.getSession(ctx.channel());

        // 2.通过消息发送方的会话信息构造要发送的消息
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setFromUserId(session.getUserId());
        messageResponsePacket.setFromUserName(session.getUserName());
        messageResponsePacket.setMessage(messageRequestPacket.getMessage());

        // 3.拿到消息接收方的 channel
        Channel toUserChannel = SessionUtil.getChannel(messageRequestPacket.getToUserId());

        // 4.将消息发送给消息接收方
        if (toUserChannel != null && SessionUtil.hasLogin(toUserChannel)) {
            toUserChannel.writeAndFlush(messageResponsePacket);
        } else {
            System.err.println("[" + messageRequestPacket.getToUserId() + "] 不在线，发送失败!");
        }
    }
}
