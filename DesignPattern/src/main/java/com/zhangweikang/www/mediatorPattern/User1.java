package com.zhangweikang.www.mediatorPattern;

/**
 * Created by zhangweikang on 2018/5/3.
 */
public class User1 extends User {
    public User1(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void work() {
        System.out.println("user1 exe!");
    }
}
