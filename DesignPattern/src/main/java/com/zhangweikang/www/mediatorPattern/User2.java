package com.zhangweikang.www.mediatorPattern;

/**
 * Created by zhangweikang on 2018/5/3.
 */
public class User2 extends User {
    public User2(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void work() {
        System.out.println("user2 exe!");
    }
}
