package com.zhangweikang.www.bridgePattern;

/**
 * Created by zhangweikang on 2018/4/27.
 */
public abstract class Bridge {

    private Sourceable sourceable;

    public Sourceable getSourceable() {
        return sourceable;
    }

    public void setSourceable(Sourceable sourceable) {
        this.sourceable = sourceable;
    }

    public void method1(){
        sourceable.method1();
    }
}
