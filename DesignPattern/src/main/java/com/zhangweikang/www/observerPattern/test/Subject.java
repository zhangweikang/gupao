package com.zhangweikang.www.observerPattern.test;

/**
 * 目标
 *
 * @author ZhangWeiKang
 * @create 2018/5/23
 */
public class Subject implements SubjectInterface{

    public void add(){
        System.out.println(" 调用add方法 ");
        //trigger(EnumObserver.ON_ADD);
    }

    public void remove(){
        System.out.println(" 调用remove方法 ");
        //trigger(EnumObserver.ON_REMOVE);
    }
}
