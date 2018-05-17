package com.zhangweikang.www.mediatorPattern;

/**
 * Created by zhangweikang on 2018/5/3.
 */
public class TestMain {
    public static void main(String[] args) {
        Mediator mediator = new MyMediator();
        mediator.createMediator();
        mediator.workAll();
    }
}
