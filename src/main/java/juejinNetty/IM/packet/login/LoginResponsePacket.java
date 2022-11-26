package juejinNetty.IM.packet.login;

import juejinNetty.IM.packet.Packet;
import juejinNetty.IM.packet.CommandNumber;
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
    private String userId;
    private String userName;
    private String reason;
    @Override
    public Byte getCommand() {
        return CommandNumber.LOGIN_RESPONSE;
    }
}
