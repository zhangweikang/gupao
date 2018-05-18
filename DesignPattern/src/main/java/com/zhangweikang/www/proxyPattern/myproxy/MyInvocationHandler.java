package com.zhangweikang.www.proxyPattern.myproxy;

import java.lang.reflect.Method;

/**
 * InvocationHandler
 *
 * @author ZhangWeiKang
 * @create 2018/5/18
 */
public interface MyInvocationHandler {

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
}
