package com.zhangweikang.www.proxyPattern.cglb;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglb代理类
 *
 * @author ZhangWeiKang
 * @create 2018/5/17
 */
public class Cglb58 implements MethodInterceptor{

    public Object getInstance(Class<?> clazz){

        Enhancer enhancer = new Enhancer();

        //自动生成的类需要继承那个类
        enhancer.setSuperclass(clazz);

        //回调当前类
        enhancer.setCallback(this);

        //创建动态类
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        System.out.println("我是58");
        System.out.println(" 已经接到你的需求 ");

        Object o1 = methodProxy.invokeSuper(o, objects);

        System.out.println(" 准备办事 ");

        return o1;
    }
}
