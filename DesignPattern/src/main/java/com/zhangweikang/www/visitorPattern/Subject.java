package com.zhangweikang.www.visitorPattern;

/**
 * Created by zhangweikang on 2018/5/3.
 */
public interface Subject {
    void accept(Visitor visitor);
    String getSubject();
}
