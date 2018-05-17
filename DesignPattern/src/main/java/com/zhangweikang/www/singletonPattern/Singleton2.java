package com.zhangweikang.www.singletonPattern;

/**
 * 懒汉式
 * 线程不安全
 * Created by zhangweikang on 2018/4/25.
 */
public class Singleton2 {

    private Singleton2(){}

    private static Singleton2 singleton = null;

    public static Singleton2 getInstance() {
        if (singleton == null) {
            singleton = new Singleton2();
        }
        return singleton;
    }
}
