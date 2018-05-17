package com.zhangweikang.www.mediatorPattern;

/**
 * Created by zhangweikang on 2018/5/3.
 */
public abstract class User {
    private Mediator mediator;

    public Mediator getMediator(){
        return mediator;
    }

    public User(Mediator mediator) {
        this.mediator = mediator;
    }

    public abstract void work();
}
