package juejinNetty.IM.packet.message.ptop;

import juejinNetty.IM.packet.Packet;
import juejinNetty.IM.packet.CommandNumber;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname MessageRequestPacket
 * @Description
 * @Date 2022/11/24 19:42
 * @Created by brain
 */
@Data
@NoArgsConstructor
public class MessageRequestPacket extends Packet {
    private String toUserId;
    private String message;

    public MessageRequestPacket(String toUserId, String message) {
        this.toUserId = toUserId;
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return CommandNumber.MESSAGE_REQUEST;
    }
}
