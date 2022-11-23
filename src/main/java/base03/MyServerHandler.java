package base03;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Classname MyServerHandler
 * @Description
 * @Date 2022/11/23 19:12
 * @Created by brain
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //接收msg消息
        ByteBuf buf = (ByteBuf) msg;
        byte[] msgByte = new byte[buf.readableBytes()];
        buf.readBytes(msgByte);
        System.out.print(new SimpleDateFormat("yyyy-MM-dd").format(new Date())  + "接收到消息：");
        System.out.println(new String(msgByte, Charset.forName("utf-8")));
    }
}
