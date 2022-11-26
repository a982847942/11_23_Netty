package juejinNetty.IM.packet.message.group;

import juejinNetty.IM.packet.Packet;
import juejinNetty.IM.packet.CommandNumber;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname JoinGroupRequestPacket
 * @Description
 * @Date 2022/11/26 14:35
 * @Created by brain
 */
@Data
@NoArgsConstructor
public class JoinGroupRequestPacket extends Packet {
    private String groupId;

    public JoinGroupRequestPacket(String groupId) {
        this.groupId = groupId;
    }

    @Override
    public Byte getCommand() {
        return CommandNumber.JOIN_GROUP_REQUEST;
    }
}
