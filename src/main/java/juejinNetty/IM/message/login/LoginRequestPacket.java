package juejinNetty.IM.message.login;

import juejinNetty.IM.message.Packet;
import lombok.Data;

import static juejinNetty.IM.message.login.LoginCommand.LOGIN_REQUEST;

/**
 * @Classname LoginRequestPacket
 * @Description
 * @Date 2022/11/23 22:38
 * @Created by brain
 */
@Data
public class LoginRequestPacket extends Packet {
    private String userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {

        return LOGIN_REQUEST;
    }
}
