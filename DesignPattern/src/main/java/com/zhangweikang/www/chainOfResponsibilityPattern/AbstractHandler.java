package com.zhangweikang.www.chainOfResponsibilityPattern;

/**
 * Created by zhangweikang on 2018/5/2.
 */
public abstract class AbstractHandler {
    private Handler handler;

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}
