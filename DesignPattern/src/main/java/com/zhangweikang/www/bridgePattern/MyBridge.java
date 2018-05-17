package com.zhangweikang.www.bridgePattern;

/**
 * Created by zhangweikang on 2018/4/27.
 */
public class MyBridge extends Bridge {
    @Override
    public void method1() {
        getSourceable().method1();
    }
}
