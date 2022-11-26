package juejinNetty.IM.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import juejinNetty.IM.util.LoginUtil;
import juejinNetty.IM.util.SessionUtil;

/**
 * @Classname AuthHandler
 * @Description
 * @Date 2022/11/25 22:50
 * @Created by brain
 */
public class AuthHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!SessionUtil.hasLogin(ctx.channel())) {
            //没有登陆的话就删除channel
            ctx.channel().close();
        } else {
            // 一行代码实现逻辑的删除  防止多次验证浪费资源与响应时间
            ctx.pipeline().remove(this);
            super.channelRead(ctx, msg);
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        if (SessionUtil.hasLogin(ctx.channel())) {
            System.out.println("当前连接登录验证完毕，无需再次验证, AuthHandler 被移除");
        } else {
            System.out.println("无登录验证，强制关闭连接!");
        }
    }
}
