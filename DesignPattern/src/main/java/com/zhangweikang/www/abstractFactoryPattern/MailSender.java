package com.zhangweikang.www.abstractFactoryPattern;

/**
 * Created by zhangweikang on 2018/4/25.
 */
public class MailSender implements Sender {
    public void send() {
        System.out.println("to send mail");
    }
}
