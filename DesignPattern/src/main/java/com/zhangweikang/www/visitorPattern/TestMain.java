package com.zhangweikang.www.visitorPattern;

/**
 * Created by zhangweikang on 2018/5/3.
 */
public class TestMain {
    public static void main(String[] args) {
        Visitor visitor = new MyVisitor();
        Subject sub = new MySubject();
        sub.accept(visitor);
    }
}
