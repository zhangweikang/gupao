package com.zhangweikang.www.factoryPattern;

/**
 * 静态工厂方法模式
 * Created by zhangweikang on 2018/4/25.
 */
public class SenderFactory3 {

    static Sender productSms() {
        return new SmsSender();
    }

    static Sender productMail() {
        return new MailSender();
    }
}
