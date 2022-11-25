package juejinNetty.IM.message.login.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import juejinNetty.IM.message.PacketCodeC;
import juejinNetty.IM.message.login.MessageRequestPacket;
import juejinNetty.IM.message.login.MessageResponsePacket;
import juejinNetty.IM.util.LoginUtil;

import java.util.Date;

/**
 * @Classname MessageRequestHandler
 * @Description
 * @Date 2022/11/25 20:25
 * @Created by brain
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) throws Exception {
        // 处理消息
        System.out.println(LoginUtil.DATE_FORMAT.format(new Date()) + ": 收到客户端消息: \n" + msg.getMessage());

        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setMessage("服务端回复\n" + msg.getMessage());
//        ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), messageResponsePacket);
        ctx.channel().writeAndFlush(messageResponsePacket);
    }
}
