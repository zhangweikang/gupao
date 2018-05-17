package com.zhangweikang.www.decoratorPattern;

/**
 * Created by zhangweikang on 2018/4/26.
 */
public class TestMain {

    public static void main(String[] args) {
        Source source = new Source();

        Sourceable sourceable = new Decorator(source);

        sourceable.method();
    }
}
