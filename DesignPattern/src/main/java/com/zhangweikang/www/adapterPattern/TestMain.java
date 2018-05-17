package com.zhangweikang.www.adapterPattern;

/**
 * Created by zhangweikang on 2018/4/26.
 */
public class TestMain {

    public static void main(String[] args) {

        /*Targetable targetable = new Adapter();*/
        /*Targetable targetable = new Wrapper(new Source());

        targetable.method1();
        targetable.method2();*/

        Sourceable sourceable = new SourceSub1();
        Sourceable sourceable1 = new SourceSub2();

        sourceable.method1();
        sourceable.method2();
        sourceable1.method1();
        sourceable1.method2();
    }
}
