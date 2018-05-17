package com.zhangweikang.www.iteratorPattern;

/**
 * Created by zhangweikang on 2018/5/2.
 */
public class TestMain {

    public static void main(String[] args) {
        Collection collection = new MyCollection();
        Iterator it = collection.iterator();

        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
}
