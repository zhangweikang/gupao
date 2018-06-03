package com.zhangweikang.www.serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * java默认序列化方式
 *
 * 1.被序列化类必须实现Serializable接口
 * 2.被transient标记的变量不会被序列化
 * 3.被序列化类如果有父类,父类必须实现Serializable接口,否则父类属性将不会被序列化
 *
 * @author ZhangWeiKang
 * @create 2018/5/31
 */
public class JavaSerializer implements ISerializer {
    @Override
    public <T>byte[] serializer(T object) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream;
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <T> T deserializer(byte[] data, Class<T> clazz) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);

            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            return (T)objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
