package juejinNetty.IM.packet.message.group;

import juejinNetty.IM.packet.Packet;
import juejinNetty.IM.packet.CommandNumber;
import lombok.Data;

/**
 * @Classname QuitGroupResponsePacket
 * @Description
 * @Date 2022/11/26 14:57
 * @Created by brain
 */
@Data
public class QuitGroupResponsePacket extends Packet {
    private String groupId;
    private boolean success;

    @Override
    public Byte getCommand() {
        return CommandNumber.QUIT_GROUP_REQUEST;
    }
}
