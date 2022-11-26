package juejinNetty.IM.client.console;

import io.netty.channel.Channel;
import juejinNetty.IM.packet.message.group.SendToGroupRequestPacket;

import java.util.Scanner;

/**
 * @Classname SendToGroupConsoleCommand
 * @Description
 * @Date 2022/11/26 20:03
 * @Created by brain
 */
public class SendToGroupConsoleCommand implements ConsoleCommand{
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("请输入群组ID:");
        String toGroupId = scanner.next();
        System.out.println("请输入需要发送给的消息:");
        StringBuilder message = new StringBuilder();
        while (!scanner.hasNext("#")) {
            message.append(scanner.nextLine() + "\r\n");
        }
        scanner.next();
        channel.writeAndFlush(new SendToGroupRequestPacket(toGroupId, message.toString()));
    }
}
