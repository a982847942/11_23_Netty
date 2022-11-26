package juejinNetty.IM.packet.message.group;

import juejinNetty.IM.packet.Packet;
import juejinNetty.IM.packet.CommandNumber;
import juejinNetty.IM.session.Session;
import lombok.Data;

/**
 * @Classname SendToGroupResponsePacket
 * @Description
 * @Date 2022/11/26 20:05
 * @Created by brain
 */
@Data
public class SendToGroupResponsePacket extends Packet {
    private  String fromGroupId;
    private String message;
    private Session fromUser;
    @Override
    public Byte getCommand() {
        return CommandNumber.SENT_TO_GROUP_RESPONSE;
    }
}
