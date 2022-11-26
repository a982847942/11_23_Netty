package juejinNetty.IM.packet.message.group;

import juejinNetty.IM.packet.Packet;
import juejinNetty.IM.packet.CommandNumber;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname SendToGroupRequestPacket
 * @Description
 * @Date 2022/11/26 20:04
 * @Created by brain
 */
@Data
@NoArgsConstructor
public class SendToGroupRequestPacket extends Packet {
    private String toGroupId;
    private String message;

    public SendToGroupRequestPacket(String toGroupId, String message) {
        this.toGroupId = toGroupId;
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return CommandNumber.SENT_TO_GROUP_REQUEST;
    }
}
