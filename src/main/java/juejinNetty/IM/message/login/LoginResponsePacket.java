package juejinNetty.IM.message.login;

import juejinNetty.IM.message.Packet;
import lombok.Data;

/**
 * @Classname LoginResponsePacket
 * @Description
 * @Date 2022/11/23 22:53
 * @Created by brain
 */
@Data
public class LoginResponsePacket extends Packet {
    private boolean success;

    private String reason;
    @Override
    public Byte getCommand() {
        return LoginCommand.LOGIN_RESPONSE;
    }
}
