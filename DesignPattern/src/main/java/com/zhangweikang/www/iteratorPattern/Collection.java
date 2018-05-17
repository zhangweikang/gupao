package com.zhangweikang.www.iteratorPattern;

/**
 * Created by zhangweikang on 2018/5/2.
 */
public interface Collection {
    public Iterator iterator();

    /*取得集合元素*/
    public Object get(int i);

    /*取得集合大小*/
    public int size();
}
