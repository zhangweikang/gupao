package com.zhangweikang.www.serializer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.JavaSerializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * kroy序列化
 *
 * 1.序列化对象,对象必须实现Serializable
 *
 * @author ZhangWeiKang
 * @create 2018/6/3
 */
public class KroySerializer implements ISerializer {
    @Override
    public <T> byte[] serializer(T obj) {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.register(obj.getClass(), new JavaSerializer());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Output output = new Output(baos);
        kryo.writeClassAndObject(output, obj);
        output.flush();
        output.close();
        byte[] b = baos.toByteArray();
        try {
            baos.flush();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return b;
    }

    @Override
    public <T> T deserializer(byte[] data, Class<T> clazz) {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.register(clazz, new JavaSerializer());
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        Input input = new Input(bais);
        return (T) kryo.readClassAndObject(input);
    }
}
