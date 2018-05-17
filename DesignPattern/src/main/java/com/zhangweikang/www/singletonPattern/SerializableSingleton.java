package com.zhangweikang.www.singletonPattern;

import java.io.Serializable;

/**
 * 序列化单例Test
 *
 * @author ZhangWeiKang
 * @create 2018/5/16
 */
public class SerializableSingleton implements Serializable{

    private SerializableSingleton(){}

    private static final SerializableSingleton serializableSingleton = new SerializableSingleton();

    public static SerializableSingleton getInstance(){
        return serializableSingleton;
    }

    /** 如果该对象被用于序列化，可以保证对象在序列化前后保持一致 */
    public Object readResolve() {
        return getInstance();
    }
}
