package com.zhangweikang.www.abstractFactoryPattern;

/**
 * Created by zhangweikang on 2018/4/25.
 */
public class SmsSender implements Sender {
    public void send() {
        System.out.println("to send sms code");
    }
}
