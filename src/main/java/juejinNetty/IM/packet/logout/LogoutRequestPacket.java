package juejinNetty.IM.packet.logout;

import juejinNetty.IM.packet.Packet;
import juejinNetty.IM.packet.CommandNumber;
import lombok.Data;

/**
 * @Classname LogoutRequestPacket
 * @Description
 * @Date 2022/11/26 13:31
 * @Created by brain
 */
@Data
public class LogoutRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return CommandNumber.LOGOUT_REQUEST;
    }
}
