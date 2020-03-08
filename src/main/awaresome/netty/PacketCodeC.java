package awaresome.netty;

import awaresome.netty.protocol.Command;
import awaresome.netty.protocol.LoginRequestPacket;
import awaresome.netty.protocol.Packet;
import awaresome.netty.serialize.Serializer;
import awaresome.netty.serialize.SerializerAlogrithm;
import awaresome.netty.serialize.impl.JSONSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 *
 * @Author : zhenhua.zhang
 * @Date: 2020-03-08 15:14
 */
public class PacketCodeC {
    //魔术
    private static final int MAGIC_NUMBER = 0x12345678;
    //packet类型
    private static final Map<Byte, Class<? extends Packet>> packetTypeMap;
    //序列化类型
    private static final Map<Byte, Serializer> serializerMap;

    static{
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(Command.LOGIN_REQUEST, LoginRequestPacket.class);

        serializerMap = new HashMap<>();
        Serializer serializer = new JSONSerializer();
        serializerMap.put(serializer.getSerializerAlogrithm(), serializer);
    }

    /**
     * 编码
     *
     * @param packet
     * @return
     */
    public ByteBuf encode(Packet packet) {
        // 1. 创建 ByteBuf 对象
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();
        // 2. 序列化 java 对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        // 3. 实际编码过程
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(SerializerAlogrithm.JSON);
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }

    /**
     * 解码
     *
     * @param byteBuf
     * @return
     */
    public Packet decode(ByteBuf byteBuf){
        //跳过 魔术
        byteBuf.skipBytes(4);
        //跳过 version
        byteBuf.skipBytes(1);
        //序列化算法
        byte serializerAlgorithm = byteBuf.readByte();
        //命令
        byte command = byteBuf.readByte();
        //数据包长度
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Serializer serializer = getSerializer(serializerAlgorithm);
        Class<? extends Packet> requestType = getRequestType(command);

        if(null != serializer && null != requestType) {
            return serializer.deserialize(requestType, bytes);
        }
        return null;
    }


    /**
     * 获取 序列化算法
     */
    private Serializer getSerializer(byte serializerAlgorithm){
        return serializerMap.get(serializerAlgorithm);
    }

    /**
     * 获取 packet类型
     */
    private Class<? extends Packet> getRequestType(byte command){
        return packetTypeMap.get(command);
    }

}
