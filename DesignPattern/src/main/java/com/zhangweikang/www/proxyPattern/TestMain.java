package com.zhangweikang.www.proxyPattern;

/**
 * Created by zhangweikang on 2018/4/26.
 */
public class TestMain {

    public static void main(String[] args) {
        Sourceable sourceable = new Proxy();

        sourceable.method();
    }
}
