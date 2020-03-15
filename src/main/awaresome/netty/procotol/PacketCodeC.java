package awaresome.netty.procotol;

import awaresome.netty.procotol.request.LoginRequestPacket;
import awaresome.netty.procotol.request.MessageRequestPacket;
import awaresome.netty.procotol.response.LoginResponsePacket;
import awaresome.netty.procotol.response.MessageResponsePacket;
import awaresome.netty.serialize.Serializer;
import awaresome.netty.serialize.impl.JSONSerializer;
import io.netty.buffer.ByteBuf;

import java.util.HashMap;
import java.util.Map;

import static awaresome.netty.procotol.command.Command.*;

/**
 * 描述: 协议编解码
 * --------------------------------------------------------------
 * | 魔数 4 | 版本号 1 | 序列化算法 1 | 指令 1 | 数据长度 4 | 数据体 N |
 * -------------------------------------------------------------
 *
 * @Author : zhenhua.zhang
 * @Date: 2020-03-15 15:06
 */
public class PacketCodeC {

    private static final int MAGIC_NUMBER = 0x12345678;
    public static final PacketCodeC INSTANCE = new PacketCodeC();

    private final Map<Byte, Class<? extends Packet>> packetTypeMap;
    private final Map<Byte, Serializer> serializerMap;

    private PacketCodeC(){
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMap.put(LOGIN_RESPONSE, LoginResponsePacket.class);
        packetTypeMap.put(MESSAGE_REQUEST, MessageRequestPacket.class);
        packetTypeMap.put(MESSAGE_RESPONSE, MessageResponsePacket.class);

        serializerMap = new HashMap<>();
        Serializer serializer = new JSONSerializer();
        serializerMap.put(serializer.getSerializerAlogrithm(), serializer);
    }

    public void encode(ByteBuf byteBuf, Packet packet) {
        // 1. 序列化 java 对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        // 2. 实际编码过程
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlogrithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
    }

    public Packet decode(ByteBuf byteBuf) {
        // 跳过 magic number
        byteBuf.skipBytes(4);

        // 跳过版本号
        byteBuf.skipBytes(1);
        // 序列化算法
        byte serializeAlgorithm = byteBuf.readByte();
        // 命令id
        byte commandId = byteBuf.readByte();

        int length = byteBuf.readInt();
        // 消息题
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends Packet> packet = getPacket(commandId);
        Serializer serializer = getSerializer(serializeAlgorithm);

        if(null != packet && null != serializer){
            return serializer.deserialize(packet, bytes);
        }
        return null;
    }

    public Serializer getSerializer(Byte alogrithmId){
        return serializerMap.get(alogrithmId);
    }

    public Class<? extends Packet> getPacket(Byte commandId){
        return packetTypeMap.get(commandId);
    }
}
