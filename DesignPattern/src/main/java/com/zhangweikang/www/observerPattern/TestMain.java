package com.zhangweikang.www.observerPattern;

/**
 * Created by zhangweikang on 2018/5/2.
 */
public class TestMain {

    public static void main(String[] args) {

        MySubject subject = new MySubject();
        subject.add(new Observer1());
        subject.add(new Observer2());

        subject.operation();
    }
}
