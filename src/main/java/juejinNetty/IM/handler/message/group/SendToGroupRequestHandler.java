package juejinNetty.IM.handler.message.group;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import juejinNetty.IM.packet.message.group.SendToGroupRequestPacket;
import juejinNetty.IM.packet.message.group.SendToGroupResponsePacket;
import juejinNetty.IM.util.SessionUtil;

/**
 * @Classname SendToGroupRequestHandler
 * @Description
 * @Date 2022/11/26 20:05
 * @Created by brain
 */
public class SendToGroupRequestHandler extends SimpleChannelInboundHandler<SendToGroupRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SendToGroupRequestPacket requestPacket) throws Exception {
        // 1.拿到 groupId 构造群聊消息的响应
        String groupId = requestPacket.getToGroupId();
        SendToGroupResponsePacket responsePacket = new SendToGroupResponsePacket();
        responsePacket.setFromGroupId(groupId);
        responsePacket.setMessage(requestPacket.getMessage());
        responsePacket.setFromUser(SessionUtil.getSession(ctx.channel()));


        // 2. 拿到群聊对应的 channelGroup，写到每个客户端
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        channelGroup.writeAndFlush(responsePacket);
    }
}
