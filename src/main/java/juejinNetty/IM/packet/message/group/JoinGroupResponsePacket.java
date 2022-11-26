package juejinNetty.IM.packet.message.group;

import juejinNetty.IM.packet.Packet;
import juejinNetty.IM.packet.CommandNumber;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname JoinGroupResponsePacket
 * @Description
 * @Date 2022/11/26 14:36
 * @Created by brain
 */
@Data
@NoArgsConstructor
public class JoinGroupResponsePacket extends Packet {
    private boolean success;
    private String groupId;
    private String reason;

    public JoinGroupResponsePacket(boolean success, String groupId, String reason) {
        this.success = success;
        this.groupId = groupId;
        this.reason = reason;
    }

    @Override
    public Byte getCommand() {
        return CommandNumber.JOIN_GROUP_RESPONSE;
    }
}
