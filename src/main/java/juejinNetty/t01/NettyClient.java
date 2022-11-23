package juejinNetty.t01;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Classname NettyClient
 * @Description
 * @Date 2022/11/23 20:09
 * @Created by brain
 */
public class NettyClient {
    // TODO: 2022/11/23
    /*
    服务端你是用NIO好处显而易见，那么客户端使用NIO的优势在哪里？
     */
    static final int MAX_RETRY = 5;
    public static void main(String[] args) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)//指定IO模型
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new FirstClientHandler());
                    }
                })
                .attr(AttributeKey.newInstance("clientName"), "nettyClient")
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true);
//        bootstrap.connect("juejin.cn", 80).addListener((future)->{
//            if (future.isSuccess()){
//                System.out.println("连接成功");
//            }else {
//                System.out.println("连接失败");
//            }
//        });
        connect(bootstrap,"juejin.cn",80,MAX_RETRY);
    }

    public static void connect(Bootstrap bootstrap,String host,int port,int retry){
        bootstrap.connect(host,port).addListener((future)->{
            if (future.isSuccess()){
                System.out.println("连接成功!");
            }else if(retry == 0){
                System.err.println("重试次数已用完，放弃连接！");
            }else {
                // 第几次重连
                int order = (MAX_RETRY - retry) + 1;
                // 本次重连的间隔
                int delay = 1 << order;
                System.err.println(new Date() + ": 连接失败，第" + order + "次重连……");
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit
                        .SECONDS);
            }
        });
    }
}
