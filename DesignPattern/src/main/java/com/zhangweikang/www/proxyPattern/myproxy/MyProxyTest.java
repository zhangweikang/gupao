package com.zhangweikang.www.proxyPattern.myproxy;

import com.zhangweikang.www.proxyPattern.Person;
import com.zhangweikang.www.proxyPattern.jdk.ZhangSan;

/**
 * @author ZhangWeiKang
 * @create 2018/5/17
 */
public class MyProxyTest {

    public static void main(String[] args) {
        try {
            Person instance = (Person)new MyMeipo().getInstance(new ZhangSan());
            instance.findJob();
            System.out.println("instace = " + instance.getClass());
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }
}
