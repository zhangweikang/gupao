package com.zhangweikang.www.proxyPattern;

/**
 * Created by zhangweikang on 2018/4/26.
 */
public class Proxy implements Sourceable {

    private Source source;

    public Proxy() {
        super();
        this.source = new Source();
    }

    public void method() {
        after();

        source.method();

        before();
    }

    private void after() {
        System.out.println("after proxy!");
    }
    private void before() {
        System.out.println("before proxy!");
    }
}
