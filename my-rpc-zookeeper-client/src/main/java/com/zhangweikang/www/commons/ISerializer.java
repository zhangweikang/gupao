package com.zhangweikang.www.commons;

/**
 * 序列化接口
 *
 * @author ZhangWeiKang
 * @create 2018/6/14
 */
public interface ISerializer {

    byte[] serializer(Object object);

    <T>T deserializer(Class<T> tClass, byte[] bytes);
}
