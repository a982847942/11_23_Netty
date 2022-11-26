package juejinNetty.IM.client.console;

import io.netty.channel.Channel;
import juejinNetty.IM.packet.message.group.JoinGroupRequestPacket;

import java.util.Scanner;

/**
 * @Classname JoinGroupConsoleCommand
 * @Description
 * @Date 2022/11/26 14:34
 * @Created by brain
 */
public class JoinGroupConsoleCommand implements ConsoleCommand{
    @Override
    public void exec(Scanner scanner, Channel channel) {
        JoinGroupRequestPacket joinGroupRequestPacket = new JoinGroupRequestPacket();

        System.out.print("输入 groupId，加入群聊：");
        String groupId = scanner.next();

        joinGroupRequestPacket.setGroupId(groupId);
        channel.writeAndFlush(joinGroupRequestPacket);
    }
}
