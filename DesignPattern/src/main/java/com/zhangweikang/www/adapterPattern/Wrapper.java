package com.zhangweikang.www.adapterPattern;

/**
 * 对象适配器
 * Created by zhangweikang on 2018/4/26.
 */
public class Wrapper implements Targetable {

    private Source source;

    public Wrapper(Source source) {
        this.source = source;
    }

    public void method1() {
        source.method1();
    }

    public void method2() {
        System.out.println("Wrapper method2");
    }
}
