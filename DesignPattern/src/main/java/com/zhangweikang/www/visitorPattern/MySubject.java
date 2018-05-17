package com.zhangweikang.www.visitorPattern;

/**
 * Created by zhangweikang on 2018/5/3.
 */
public class MySubject implements Subject {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String getSubject() {
        return "love";
    }
}
