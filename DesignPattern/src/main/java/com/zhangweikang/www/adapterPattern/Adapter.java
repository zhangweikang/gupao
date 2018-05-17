package com.zhangweikang.www.adapterPattern;

/**
 * 类适配器
 * Created by zhangweikang on 2018/4/26.
 */
public class Adapter extends Source implements Targetable {
    public void method2() {
        System.out.println("Adapter method2");
    }
}
