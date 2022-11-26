package juejinNetty.IM.client.console;

import io.netty.channel.Channel;
import juejinNetty.IM.packet.message.group.ListGroupMembersRequestPacket;

import java.util.Scanner;

/**
 * @Classname ListGroupMembersConsoleCommand
 * @Description
 * @Date 2022/11/26 15:22
 * @Created by brain
 */
public class ListGroupMembersConsoleCommand implements ConsoleCommand{
    @Override
    public void exec(Scanner scanner, Channel channel) {
        ListGroupMembersRequestPacket listGroupMembersRequestPacket = new ListGroupMembersRequestPacket();

        System.out.print("输入 groupId，获取群成员列表：");
        String groupId = scanner.next();

        listGroupMembersRequestPacket.setGroupId(groupId);
        channel.writeAndFlush(listGroupMembersRequestPacket);
    }
}
