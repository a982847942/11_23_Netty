package juejinNetty.IM.packet.message.group;

import juejinNetty.IM.packet.Packet;
import juejinNetty.IM.packet.CommandNumber;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname ListGroupMembersRequestPacket
 * @Description
 * @Date 2022/11/26 15:23
 * @Created by brain
 */
@Data
@NoArgsConstructor
public class ListGroupMembersRequestPacket extends Packet {
private String groupId;

    public ListGroupMembersRequestPacket(String groupId) {
        this.groupId = groupId;
    }

    @Override
    public Byte getCommand() {
        return CommandNumber.LIST_GROUP_MEMBERS_REQUEST;
    }
}
