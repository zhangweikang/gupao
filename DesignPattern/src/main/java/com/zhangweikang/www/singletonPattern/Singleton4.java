package com.zhangweikang.www.singletonPattern;

/**
 * 饿汉式
 * 线程安全,容易产生大量的垃圾对象
 * Created by zhangweikang on 2018/4/25.
 */
public class Singleton4 {

    /**
     * 私有化构造方法,防止被实例化
     */
    private Singleton4() {
    }

    /** 此处使用一个内部类来维护单例 */
    private static class SingletonFactory{
        private static Singleton4 singleton = new Singleton4();
    }

    /** 获取实例 */
    Singleton4 getInstance(){
        return SingletonFactory.singleton;
    }

    /** 如果该对象被用于序列化，可以保证对象在序列化前后保持一致 */
    public Object readResolve() {
        return getInstance();
    }
}
