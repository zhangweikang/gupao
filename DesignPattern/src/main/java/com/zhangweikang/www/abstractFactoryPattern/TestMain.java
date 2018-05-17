package com.zhangweikang.www.abstractFactoryPattern;

/**
 * 抽象工厂模式
 * Created by zhangweikang on 2018/4/25.
 */
public class TestMain {

    public static void main(String[] args) {
        /*Provider provider = new SmsSendFactory();
        provider.product().send();*/

        AbstractProviderFactory abstractProviderFactory = new AbstractProviderFactory();
        abstractProviderFactory.getMailSender().send();
    }
}
