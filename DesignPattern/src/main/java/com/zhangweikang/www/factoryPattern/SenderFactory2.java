package com.zhangweikang.www.factoryPattern;

/**
 * 多个工厂方法模式
 * Created by zhangweikang on 2018/4/25.
 */
public class SenderFactory2 {

    Sender productSms() {
        return new SmsSender();
    }

    Sender productMail() {
        return new MailSender();
    }
}
