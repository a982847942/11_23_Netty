package juejinNetty.IM.message.login;

import juejinNetty.IM.message.Packet;
import lombok.Data;

/**
 * @Classname MessageResponsePacket
 * @Description
 * @Date 2022/11/24 19:44
 * @Created by brain
 */
@Data
public class MessageResponsePacket extends Packet {
    private String message;
    @Override
    public Byte getCommand() {
        return LoginCommand.MESSAGE_RESPONSE;
    }
}
