package com.zhangweikang.www.proxyPattern.jdk;

import com.zhangweikang.www.proxyPattern.Person;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理类
 *
 * @author ZhangWeiKang
 * @create 2018/5/17
 */
public class JDKMeipo implements InvocationHandler{

    private Person person;

    public Object getInstance(Person person){
        this.person = person;

        Class<?> aClass = person.getClass();

        return Proxy.newProxyInstance(aClass.getClassLoader(), aClass.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("我是媒婆");
        System.out.println(" 已经接到你的需求 ");

        Object invoke = method.invoke(person, args);

        System.out.println(" 准备办事 ");

        return invoke;
    }
}
