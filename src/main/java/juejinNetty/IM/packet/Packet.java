package juejinNetty.IM.packet;

import lombok.Data;

/**
 * @Classname Packet
 * @Description
 * @Date 2022/11/23 22:31
 * @Created by brain
 */
@Data
public abstract class Packet {
    /*
    魔数 4
    版本号 1
    序列化协议 1
    指令 1
    长度 4
    数据
     */

    /*
    版本
     */
    private Byte version = 1;

    /*
    指令号
     */
    public abstract Byte getCommand();


}
