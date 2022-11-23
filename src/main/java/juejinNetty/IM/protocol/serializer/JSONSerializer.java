package juejinNetty.IM.protocol.serializer;


import com.alibaba.fastjson.JSON;

/**
 * @Classname JSONSerializer
 * @Description
 * @Date 2022/11/23 22:42
 * @Created by brain
 */
public class JSONSerializer implements Serializer{
    @Override
    public byte getSerializerAlgorithm() {

        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {

        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {

        return JSON.parseObject(bytes, clazz);
    }
}
