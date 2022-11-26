package juejinNetty.IM.packet.message.group;

import juejinNetty.IM.packet.Packet;
import juejinNetty.IM.packet.CommandNumber;
import lombok.Data;

import java.util.List;

/**
 * @Classname CreateGroupRequestPacket
 * @Description
 * @Date 2022/11/26 11:33
 * @Created by brain
 */
@Data
public class CreateGroupRequestPacket extends Packet {
    private List<String> userIdList;

    @Override
    public Byte getCommand() {
        return CommandNumber.CREATE_GROUP_REQUEST;
    }
}
