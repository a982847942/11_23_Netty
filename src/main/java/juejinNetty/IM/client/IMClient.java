package juejinNetty.IM.client;


import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import juejinNetty.IM.message.PacketCodeC;
import juejinNetty.IM.message.PacketDecoder;
import juejinNetty.IM.message.PacketEncoder;
import juejinNetty.IM.message.Spliter;
import juejinNetty.IM.message.login.MessageRequestPacket;
import juejinNetty.IM.message.login.handler.LoginResponseHandler;
import juejinNetty.IM.message.login.handler.MessageResponseHandler;
import juejinNetty.IM.util.LoginUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @Classname IMClient
 * @Description
 * @Date 2022/11/24 19:52
 * @Created by brain
 */
public class IMClient {
    private static final String HOST = "127.0.0.1";
    private static final Integer PORT = 8000;
    private static final Integer MAX_RETRY = 5;
//    private static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        start();
    }

    private static void start(){
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup worker = new NioEventLoopGroup();

        bootstrap.group(worker)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
//                        ch.pipeline().addLast(new ClientHandler());
                        ch.pipeline().addLast(new Spliter());
                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new LoginResponseHandler());
                        ch.pipeline().addLast(new MessageResponseHandler());
                        ch.pipeline().addLast(new PacketEncoder());
                    }
                });
        connect(bootstrap,PORT,HOST,MAX_RETRY);
    }
    private static void connect(Bootstrap bootstrap,int port, String host,  int retry) {
        bootstrap.connect(host,port).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()){
                    System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ": 连接成功!");
                    Channel channel = ((ChannelFuture) future).channel();
                    startConsoleThread(channel);
                }else if(retry == 0){
                    System.err.println("重试次数已用完，放弃连接！");
                }else {
                    //第几次重连
                    int order = (MAX_RETRY - retry) + 1;
                    // 本次重连的间隔
                    int delay = 1 << order;
                    System.err.println(new SimpleDateFormat("yyyy-MM-dd").format(new Date())
                            + ": 连接失败，第" + order + "次重连……");
                    //拿到client的配置  获取它的工作线程组 选择一个线程进行任务调度
                    bootstrap.config().group().schedule(() -> connect(bootstrap, port, host, retry - 1), delay, TimeUnit
                            .SECONDS);
                }
            }
        });
    }
    private static void startConsoleThread(Channel channel) {
        new Thread(()->{
            while(!Thread.interrupted()){
                if (LoginUtil.hasLogin(channel)){
                    Scanner sc = new Scanner(System.in);
                    System.out.println("请输入需要发送给的消息:");
                    StringBuilder message = new StringBuilder();
                    while(!sc.hasNext("#")){
                        message.append(sc.next() + "\r\n");
                    }
                    MessageRequestPacket requestPacket = new MessageRequestPacket();
                    requestPacket.setMessage(message.toString());
//                    ByteBuf buf = PacketCodeC.INSTANCE.encode(channel.alloc(), requestPacket);
                    channel.writeAndFlush(requestPacket);
                }
            }
        }).start();
    }
}
