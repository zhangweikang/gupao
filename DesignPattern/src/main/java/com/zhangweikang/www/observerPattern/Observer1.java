package com.zhangweikang.www.observerPattern;

/**
 * Created by zhangweikang on 2018/5/2.
 */
public class Observer1 implements Observer {
    public void update() {
        System.out.println("observer1 has received!");
    }
}
