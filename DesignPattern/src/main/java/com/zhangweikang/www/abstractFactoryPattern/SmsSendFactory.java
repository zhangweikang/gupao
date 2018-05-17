package com.zhangweikang.www.abstractFactoryPattern;

/**
 * Created by zhangweikang on 2018/4/25.
 */
public class SmsSendFactory implements Provider {

    public Sender product() {
        return new SmsSender();
    }
}
