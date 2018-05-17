package com.zhangweikang.www.factoryPattern;

/**
 * 普通工厂模式
 * Created by zhangweikang on 2018/4/25.
 */
public class SenderFactory {

    Sender product(String type){
        if ("sms".equals(type)){
            return new SmsSender();
        } else if ("mail".equals(type)){
            return new MailSender();
        } else {
            System.out.println("type = " + type + ";类型不存在");
            return null;
        }
    }
}
