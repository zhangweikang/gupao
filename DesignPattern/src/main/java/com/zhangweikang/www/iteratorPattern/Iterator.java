package com.zhangweikang.www.iteratorPattern;

/**
 * Created by zhangweikang on 2018/5/2.
 */
public interface Iterator {
    //前移
    public Object previous();

    //后移
    public Object next();
    public boolean hasNext();

    //取得第一个元素
    public Object first();
}
