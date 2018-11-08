package com.zhangweikang.www.abstractFactoryPattern;

/**
 * 抽象类
 *
 * @author ZhangWeiKang
 * @create 2018/5/16
 */
public class AbstractProviderFactory {
111111
    public Sender getMailSender(){
        return new MailSendFactory().product();
    }

    public Sender getSmsSender(){
        return new SmsSendFactory().product();
    }
}
