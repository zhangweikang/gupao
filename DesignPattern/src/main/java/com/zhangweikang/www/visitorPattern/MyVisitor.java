package com.zhangweikang.www.visitorPattern;

/**
 * Created by zhangweikang on 2018/5/3.
 */
public class MyVisitor implements Visitor {
    @Override
    public void visit(Subject sub) {
        System.out.println("visit the subject："+sub.getSubject());
    }
}
