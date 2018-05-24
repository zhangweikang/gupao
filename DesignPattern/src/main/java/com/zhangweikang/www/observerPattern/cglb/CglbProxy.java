package com.zhangweikang.www.observerPattern.cglb;

import com.zhangweikang.www.observerPattern.event.Event;
import com.zhangweikang.www.observerPattern.event.EventLisenter;
import com.zhangweikang.www.observerPattern.test.EnumObserver;
import com.zhangweikang.www.observerPattern.test.Observer;
import com.zhangweikang.www.observerPattern.test.Subject;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglb代理类
 *
 * @author ZhangWeiKang
 * @create 2018/5/23
 */
public class CglbProxy extends EventLisenter implements MethodInterceptor{

    private Class<Subject> subject;

    public Object getInstance(Class<Subject> subject){
        this.subject = subject;

        Enhancer enhancer = new Enhancer();

        //设置代理类父类
        enhancer.setSuperclass(subject);

        //设置回调
        enhancer.setCallback(this);

        //创建代理类
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        Object o1 = methodProxy.invokeSuper(o, objects);
        Observer observer = new Observer();
        Method advice = Observer.class.getMethod("advice", Event.class);
        this.addLisenter(EnumObserver.ON_ADD,observer,advice);
        this.trigger(EnumObserver.ON_ADD);
        return o1;
    }
}
