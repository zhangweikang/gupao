package com.zhangweikang.www.commons;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

/**
 * protobuf序列化
 *
 * @author ZhangWeiKang
 * @create 2018/6/14
 */
public class ProtosbufSerializer implements ISerializer {

    public byte[] serializer(Object object) {
        Schema schema = RuntimeSchema.getSchema(object.getClass());
        return ProtobufIOUtil.toByteArray(object, schema, LinkedBuffer.allocate(256));
    }

    public <T> T deserializer(Class<T> clazz, byte[] data) {
        try {
            T t = clazz.newInstance();
            Schema<T> schema = RuntimeSchema.getSchema(clazz);
            ProtobufIOUtil.mergeFrom(data, t, schema);

            return t;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
