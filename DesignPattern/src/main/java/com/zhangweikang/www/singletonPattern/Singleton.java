package com.zhangweikang.www.singletonPattern;

import java.io.Serializable;

/**
 * 饿汉式
 * 线程安全,容易产生大量的垃圾对象
 * Created by zhangweikang on 2018/4/25.
 */
public class Singleton implements Serializable {

    private Singleton(){}

    private static final Singleton singleton = new Singleton();

    public static Singleton  getInstance(){
        System.out.println("time = " +System.currentTimeMillis()+";singleton = "+ singleton);
        return singleton;
    }
}
