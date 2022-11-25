package juejinNetty.IM.message;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @Classname Spliter
 * @Description
 * @Date 2022/11/25 21:18
 * @Created by brain
 */
public class Spliter extends LengthFieldBasedFrameDecoder {
    /*
    四个拆包器 应用层分包
    FixedLengthFrameDecoder
    LineBasedFrameDecoder
    DelimiterBasedFrameDecoder
    LengthFieldBasedFrameDecoder
     */
    private static final int LENGTH_FIELD_OFFSET = 7;
    private static final int LENGTH_FIELD_LENGTH = 4;

    public Spliter() {
        //数据报最大长度 长度域偏移值 长度域字节数
        super(Integer.MAX_VALUE, LENGTH_FIELD_OFFSET, LENGTH_FIELD_LENGTH);
    }

    /*
    注意这里的in是Netty框架保证的每次传递进来的时候，均为一个数据包的开头
     */
    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        System.out.println("haha");
        if (in.getInt(in.readerIndex()) != PacketCodeC.MAGIC_NUMBER) {
            ctx.channel().close();
            return null;
        }
        return super.decode(ctx, in);
    }
}
