package awaresome.netty.serialize;

import awaresome.netty.serialize.impl.JSONSerializer;

/**
 *
 */
public interface Serializer {

    Serializer DEFAULT = new JSONSerializer();

    /**
     * 序列化算法
     * @return
     */
    byte getSerializerAlogrithm();

    /**
     * java 对象转二进制
     * @param object
     * @return
     */
    byte[] serialize(Object object);

    /**
     * todo 二进制转换成 java 对象
     * @param clazz
     * @param bytes
     * @param <T>
     * @return
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);

}
