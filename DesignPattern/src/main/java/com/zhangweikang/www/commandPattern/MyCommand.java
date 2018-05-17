package com.zhangweikang.www.commandPattern;

/**
 * Created by zhangweikang on 2018/5/3.
 */
public class MyCommand implements Command {
    private Receiver receiver;

    public MyCommand(Receiver receiver) {
        this.receiver = receiver;
    }
    @Override
    public void exe() {
        receiver.action();
    }
}
