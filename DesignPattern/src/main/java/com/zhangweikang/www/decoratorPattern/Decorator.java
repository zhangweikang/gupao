package com.zhangweikang.www.decoratorPattern;

/**
 * Created by zhangweikang on 2018/4/26.
 */
public class Decorator implements Sourceable {

    private Source source;

    public Decorator(Source source) {
        this.source = source;
    }

    public void method() {
        System.out.println(" before method ");

        source.method();

        System.out.println(" after method ");
    }
}
