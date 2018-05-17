package com.zhangweikang.www.abstractFactoryPattern;

/**
 * Created by zhangweikang on 2018/4/25.
 */
public class MailSendFactory implements Provider {
    public Sender product() {
        return new MailSender();
    }
}
