package juejinNetty.IM.packet.message.group;

import juejinNetty.IM.packet.Packet;
import juejinNetty.IM.packet.CommandNumber;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Classname CreateGroupResponsePacket
 * @Description
 * @Date 2022/11/26 11:36
 * @Created by brain
 */
@Data
@NoArgsConstructor
public class CreateGroupResponsePacket extends Packet {
    private boolean success;
    private String groupId;
    private List<String> userNameList;

    public CreateGroupResponsePacket(boolean success, String groupId, List<String> userNameList) {
        this.success = success;
        this.groupId = groupId;
        this.userNameList = userNameList;
    }

    @Override
    public Byte getCommand() {
        return CommandNumber.CREATE_GROUP_RESPONSE;
    }
}
