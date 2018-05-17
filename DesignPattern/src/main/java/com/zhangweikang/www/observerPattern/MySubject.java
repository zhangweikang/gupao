package com.zhangweikang.www.observerPattern;

/**
 * Created by zhangweikang on 2018/5/2.
 */
public class MySubject extends AbstractSubject {

    @Override
    public void operation() {
        System.out.println(" this mySubject operation ");
        this.notifyObservers();
    }
}
