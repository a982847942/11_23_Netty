package juejinNetty.IM.packet.message.ptop;

import juejinNetty.IM.packet.Packet;
import juejinNetty.IM.packet.CommandNumber;
import lombok.Data;

/**
 * @Classname MessageResponsePacket
 * @Description
 * @Date 2022/11/24 19:44
 * @Created by brain
 */
@Data
public class MessageResponsePacket extends Packet {
    private String fromUserId;
    private String fromUserName;
    private String message;
    @Override
    public Byte getCommand() {
        return CommandNumber.MESSAGE_RESPONSE;
    }
}
