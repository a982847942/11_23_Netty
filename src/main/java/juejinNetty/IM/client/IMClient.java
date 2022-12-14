package juejinNetty.IM.client;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import juejinNetty.IM.client.console.ConsoleCommandManager;
import juejinNetty.IM.client.console.LoginConsoleCommand;
import juejinNetty.IM.handler.Spliter;
import juejinNetty.IM.handler.message.group.*;
import juejinNetty.IM.handler.login.LoginResponseHandler;
import juejinNetty.IM.handler.message.ptop.MessageResponseHandler;
import juejinNetty.IM.packet.PacketDecoder;
import juejinNetty.IM.packet.PacketEncoder;
import juejinNetty.IM.util.SessionUtil;

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
                        ch.pipeline().addLast(new JoinGroupResponseHandler());
                        ch.pipeline().addLast(new QuitGroupResponseHandler());
                        ch.pipeline().addLast(new ListGroupMembersResponseHandler());
                        ch.pipeline().addLast(new SendToGroupResponseHandler());
                        ch.pipeline().addLast(new CreateGroupResponseHandler());
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
                    System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ": ????????????!");
                    Channel channel = ((ChannelFuture) future).channel();
                    startConsoleThread(channel);
                }else if(retry == 0){
                    System.err.println("???????????????????????????????????????");
                }else {
                    //???????????????
                    int order = (MAX_RETRY - retry) + 1;
                    // ?????????????????????
                    int delay = 1 << order;
                    System.err.println(new SimpleDateFormat("yyyy-MM-dd").format(new Date())
                            + ": ??????????????????" + order + "???????????????");
                    //??????client?????????  ??????????????????????????? ????????????????????????????????????
                    bootstrap.config().group().schedule(() -> connect(bootstrap, port, host, retry - 1), delay, TimeUnit
                            .SECONDS);
                }
            }
        });
    }
//    private static void startConsoleThread(Channel channel) {
//        new Thread(()->{
//            while(!Thread.interrupted()){
//                Scanner sc = new Scanner(System.in);
//                if (SessionUtil.hasLogin(channel)){
//                    MessageRequestPacket requestPacket = new MessageRequestPacket();
//                    System.out.print("??????????????????ID:");
//                    String toUserId = sc.next();
//                    requestPacket.setToUserId(toUserId);
//                    System.out.println("?????????????????????????????????:");
//                    StringBuilder message = new StringBuilder();
//                    while(!sc.hasNext("#")){
//                        message.append(sc.nextLine() + "\r\n");
//                    }
//                    requestPacket.setMessage(message.toString());
////                    ByteBuf buf = PacketCodeC.INSTANCE.encode(channel.alloc(), requestPacket);
//                    channel.writeAndFlush(requestPacket);
//                    waitForLoginResponse();
//                }else {
//                    LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
//                    System.out.print("?????????????????????: ");
//                    String username = sc.nextLine();
//                    loginRequestPacket.setUserName(username);
//                    // ?????????????????????
//                    loginRequestPacket.setPassword("pwd");
//                    // ?????????????????????
//                    channel.writeAndFlush(loginRequestPacket);
//                    waitForLoginResponse();
//                }
//            }
//        }).start();
//    }
private static void startConsoleThread(Channel channel) {
    ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager();
    LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();
    Scanner scanner = new Scanner(System.in);

    new Thread(() -> {
        while (!Thread.interrupted()) {
            if (!SessionUtil.hasLogin(channel)) {
                loginConsoleCommand.exec(scanner, channel);
                while (!SessionUtil.hasLogin(channel));
            } else {
                consoleCommandManager.exec(scanner, channel);
            }
        }
    }).start();
}

    private static void waitForLoginResponse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
    }
}
