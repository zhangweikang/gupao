package com.zhangweikang.www.proxyPattern.myproxy;

import com.zhangweikang.www.proxyPattern.Person;

import java.lang.reflect.Method;

/**
 * 代理类
 *
 * @author ZhangWeiKang
 * @create 2018/5/18
 */
public class MyMeipo implements MyInvocationHandler{

    private Person person;

    public Object getInstance(Person person){
        this.person = person;

        Class<?> aClass = person.getClass();

        return MyProxy.newProxyInstance(new MyClassLoader(),aClass.getInterfaces(),this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("我是媒婆");
        System.out.println(" 已经接到你的需求 ");
        Object invoke = method.invoke(this.person, args);
        System.out.println(" 开始办事 ");
        return invoke;
    }
}
