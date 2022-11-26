package juejinNetty.IM.packet.message.group;

import juejinNetty.IM.packet.Packet;
import juejinNetty.IM.packet.CommandNumber;
import juejinNetty.IM.session.Session;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Classname ListGroupMembersResponsePacket
 * @Description
 * @Date 2022/11/26 15:24
 * @Created by brain
 */
@Data
@NoArgsConstructor
public class ListGroupMembersResponsePacket extends Packet {
    private String groupId;
    private List<Session> sessionList;

    public ListGroupMembersResponsePacket(String groupId, List<Session> sessionList) {
        this.groupId = groupId;
        this.sessionList = sessionList;
    }

    @Override
    public Byte getCommand() {
        return CommandNumber.LIST_GROUP_MEMBERS_RESPONSE;
    }
}
