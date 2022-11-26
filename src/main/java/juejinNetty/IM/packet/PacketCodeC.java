package juejinNetty.IM.packet;

import io.netty.buffer.ByteBuf;
import juejinNetty.IM.packet.login.LoginRequestPacket;
import juejinNetty.IM.packet.login.LoginResponsePacket;
import juejinNetty.IM.packet.logout.LogoutRequestPacket;
import juejinNetty.IM.packet.logout.LogoutResponsePacket;
import juejinNetty.IM.packet.message.group.*;
import juejinNetty.IM.packet.message.ptop.MessageRequestPacket;
import juejinNetty.IM.packet.message.ptop.MessageResponsePacket;
import juejinNetty.IM.protocol.serializer.JSONSerializer;
import juejinNetty.IM.protocol.serializer.Serializer;

import java.util.HashMap;
import java.util.Map;

import static juejinNetty.IM.packet.CommandNumber.*;

/**
 * @Classname PacketCodeC
 * @Description
 * @Date 2022/11/23 22:51
 * @Created by brain
 */
public class PacketCodeC {
    //魔数
    public static final int MAGIC_NUMBER = 0x12345678;
    private static final Map<Byte, Class<? extends Packet>> packetTypeMap;
    private static final Map<Byte, Serializer> serializerMap;
    public static final PacketCodeC INSTANCE = new PacketCodeC();

    static {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMap.put(LOGIN_RESPONSE, LoginResponsePacket.class);
        packetTypeMap.put(MESSAGE_REQUEST, MessageRequestPacket.class);
        packetTypeMap.put(MESSAGE_RESPONSE, MessageResponsePacket.class);
        packetTypeMap.put(LOGOUT_REQUEST, LogoutRequestPacket.class);
        packetTypeMap.put(LOGOUT_RESPONSE, LogoutResponsePacket.class);
        packetTypeMap.put(CREATE_GROUP_REQUEST, CreateGroupRequestPacket.class);
        packetTypeMap.put(CREATE_GROUP_RESPONSE, CreateGroupResponsePacket.class);
        packetTypeMap.put(JOIN_GROUP_REQUEST, JoinGroupRequestPacket.class);
        packetTypeMap.put(JOIN_GROUP_RESPONSE, JoinGroupResponsePacket.class);
        packetTypeMap.put(QUIT_GROUP_REQUEST, QuitGroupRequestPacket.class);
        packetTypeMap.put(QUIT_GROUP_RESPONSE, QuitGroupResponsePacket.class);
        packetTypeMap.put(LIST_GROUP_MEMBERS_REQUEST, ListGroupMembersRequestPacket.class);
        packetTypeMap.put(LIST_GROUP_MEMBERS_RESPONSE, ListGroupMembersResponsePacket.class);
        packetTypeMap.put(SENT_TO_GROUP_REQUEST,SendToGroupRequestPacket.class);
        packetTypeMap.put(SENT_TO_GROUP_RESPONSE,SendToGroupResponsePacket.class);
        serializerMap = new HashMap<>();
        Serializer serializer = new JSONSerializer();
        serializerMap.put(serializer.getSerializerAlgorithm(), serializer);
    }

    private PacketCodeC() {
    }

    public ByteBuf encode(ByteBuf byteBuf, Packet packet) {
        // 1. 创建 ByteBuf 对象
        /*
        ioBuffer() 方法会返回适配 io 读写相关的内存，它会尽可能创建一个直接内存，
        直接内存可以理解为不受 jvm 堆管理的内存空间，写到 IO 缓冲区的效果更高。
         */
//        ByteBuf byteBuf = byteBufAllocator.ioBuffer();
        // 2. 序列化 java 对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        // 3. 实际编码过程
        //魔数
        byteBuf.writeInt(MAGIC_NUMBER);
        //版本号
        byteBuf.writeByte(packet.getVersion());
        //序列化方式
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        //指令码
        byteBuf.writeByte(packet.getCommand());
        //数据长度
        byteBuf.writeInt(bytes.length);
        //具体数据
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }

    /**
     * 解码
     * @param byteBuf
     * @return
     */
    public Packet decode(ByteBuf byteBuf) {
        // 跳过 magic number
        byteBuf.skipBytes(4);

        // 跳过版本号
        byteBuf.skipBytes(1);

        // 序列化算法标识
        byte serializeAlgorithm = byteBuf.readByte();

        // 指令
        byte command = byteBuf.readByte();

        // 数据包长度
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        //根据指令类型返回其对应的类类型
        Class<? extends Packet> requestType = getRequestType(command);
        //根据序列化算法的类型返回对应的序列化算法实例
        Serializer serializer = getSerializer(serializeAlgorithm);

        if (requestType != null && serializer != null) {
            //用对应的序列化算法去反序列化成对应的类型
            return serializer.deserialize(requestType, bytes);
        }

        return null;
    }




    private Serializer getSerializer(byte serializeAlgorithm) {

        return serializerMap.get(serializeAlgorithm);
    }

    private Class<? extends Packet> getRequestType(byte command) {

        return packetTypeMap.get(command);
    }
}
