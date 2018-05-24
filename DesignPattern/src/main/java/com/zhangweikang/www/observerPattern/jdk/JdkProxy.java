package com.zhangweikang.www.observerPattern.jdk;

import com.zhangweikang.www.observerPattern.event.Event;
import com.zhangweikang.www.observerPattern.event.EventLisenter;
import com.zhangweikang.www.observerPattern.test.EnumObserver;
import com.zhangweikang.www.observerPattern.test.Observer;
import com.zhangweikang.www.observerPattern.test.Subject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk代理
 *
 * @author ZhangWeiKang
 * @create 2018/5/23
 */
public class JdkProxy extends EventLisenter implements InvocationHandler{

    private Subject subject;

    public Object getInstance(Class<Subject> clazz){
        try {
            this.subject = clazz.newInstance();
            return Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Observer observer = new Observer();
        Method advice = Observer.class.getMethod("advice", Event.class);
        this.addLisenter(EnumObserver.ON_ADD,observer,advice);
        this.trigger(EnumObserver.ON_ADD);
        Object invoke = method.invoke(this.subject, args);
        return invoke;
    }
}
