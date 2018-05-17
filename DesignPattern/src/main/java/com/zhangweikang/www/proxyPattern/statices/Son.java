package com.zhangweikang.www.proxyPattern.statices;

import com.zhangweikang.www.proxyPattern.Person;

/**
 * 儿子
 *
 * @author ZhangWeiKang
 * @create 2018/5/17
 */
public class Son implements Person {

    public void findLove(){
        System.out.println("找对象,漂亮");
    }

    @Override
    public void findJob() {

    }
}
