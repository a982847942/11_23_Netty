package juejinNetty.IM.packet.logout;

import juejinNetty.IM.packet.Packet;
import juejinNetty.IM.packet.CommandNumber;
import lombok.Data;

/**
 * @Classname LogoutResponsePacket
 * @Description
 * @Date 2022/11/26 13:30
 * @Created by brain
 */
@Data
public class LogoutResponsePacket extends Packet {
    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {

        return CommandNumber.LOGOUT_RESPONSE;
    }
}
