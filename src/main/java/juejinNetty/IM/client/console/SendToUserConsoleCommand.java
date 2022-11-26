package juejinNetty.IM.client.console;

import io.netty.channel.Channel;
import juejinNetty.IM.packet.message.ptop.MessageRequestPacket;
import juejinNetty.IM.util.SessionUtil;

import java.util.Scanner;

/**
 * @Classname SendToUserConsoleCommand
 * @Description
 * @Date 2022/11/26 13:12
 * @Created by brain
 */
public class SendToUserConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        if (SessionUtil.hasLogin(channel)) {
            MessageRequestPacket requestPacket = new MessageRequestPacket();
            System.out.print("请输入接收方ID:");
            String toUserId = scanner.next();
            requestPacket.setToUserId(toUserId);
            System.out.println("请输入需要发送给的消息:");
            StringBuilder message = new StringBuilder();
            while (!scanner.hasNext("#")) {
                message.append(scanner.nextLine() + "\r\n");
            }
            scanner.next();
            requestPacket.setMessage(message.toString());
//                    ByteBuf buf = PacketCodeC.INSTANCE.encode(channel.alloc(), requestPacket);
            channel.writeAndFlush(requestPacket);
            waitForLoginResponse();
        }
    }
    private static void waitForLoginResponse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
    }
}
