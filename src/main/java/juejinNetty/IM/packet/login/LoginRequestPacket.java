package juejinNetty.IM.packet.login;

import juejinNetty.IM.packet.Packet;
import lombok.Data;

import static juejinNetty.IM.packet.CommandNumber.LOGIN_REQUEST;

/**
 * @Classname LoginRequestPacket
 * @Description
 * @Date 2022/11/23 22:38
 * @Created by brain
 */
@Data
public class LoginRequestPacket extends Packet {
    private String userId;

    private String userName;

    private String password;

    @Override
    public Byte getCommand() {

        return LOGIN_REQUEST;
    }
}
