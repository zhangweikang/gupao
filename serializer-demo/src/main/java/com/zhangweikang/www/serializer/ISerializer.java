package com.zhangweikang.www.serializer;

/**
 * 序列化接口
 *
 * @author ZhangWeiKang
 * @create 2018/5/31
 */
public interface ISerializer {

    <T>byte[] serializer(T t);

    <T>T deserializer(byte[] data,Class<T> clazz);
}
