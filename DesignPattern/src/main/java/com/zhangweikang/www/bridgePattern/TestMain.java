package com.zhangweikang.www.bridgePattern;

/**
 * Created by zhangweikang on 2018/4/27.
 */
public class TestMain {

    public static void main(String[] args) {
        Bridge bridge = new MyBridge();

        Sourceable sourceable = new SourceSub1();
        Sourceable sourceable1 = new SourceSub2();
        bridge.setSourceable(sourceable);
        bridge.method1();

        bridge.setSourceable(sourceable1);
        bridge.method1();
    }
}
