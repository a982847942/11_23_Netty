package juejinNetty.IM.message.login;

import juejinNetty.IM.message.Packet;
import lombok.Data;

/**
 * @Classname MessageRequestPacket
 * @Description
 * @Date 2022/11/24 19:42
 * @Created by brain
 */
@Data
public class MessageRequestPacket extends Packet {
    private String message;
    @Override
    public Byte getCommand() {
        return LoginCommand.MESSAGE_REQUEST;
    }
}
