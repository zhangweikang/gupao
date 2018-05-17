package com.zhangweikang.www.factoryPattern;

/**
 * 工厂模式
 * Created by zhangweikang on 2018/4/25.
 */
public class TestMain {

    public static void main(String[] args) {
        /*SenderFactory senderFactory = new SenderFactory();
        Sender sms = senderFactory.product("sms");
        sms.send();*/
        /*SenderFactory2 senderFactory = new SenderFactory2();
        Sender sender = senderFactory.productMail();
        sender.send();*/
        SenderFactory3.productSms().send();
    }
}
