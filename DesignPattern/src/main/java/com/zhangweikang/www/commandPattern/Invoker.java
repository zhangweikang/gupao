package com.zhangweikang.www.commandPattern;

/**
 * Created by zhangweikang on 2018/5/3.
 */
public class Invoker {
    private Command command;

    public Invoker(Command command) {
        this.command = command;
    }

    public void action(){
        command.exe();
    }
}
