package com.zhangweikang.www.observerPattern;

/**
 * Created by zhangweikang on 2018/5/2.
 */
public class Observer2 implements Observer {
    public void update() {
        System.out.println("observer2 has received!");
    }
}
