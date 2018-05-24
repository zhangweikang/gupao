package com.zhangweikang.www.observerPattern.test;

import com.zhangweikang.www.observerPattern.jdk.JdkProxy;

/**
 * test
 *
 * @author ZhangWeiKang
 * @create 2018/5/23
 */
public class ObserverTest {

    public static void main(String[] args) {
        try {
            /*//观察者
            Observer observer = new Observer();
            Method advice = Observer.class.getMethod("advice", Event.class);

            //被观察者
            Subject subject = new Subject();
            //注册事件
            //事件类型:EnumObserver.ON_ADD
            //观察者:observer
            //触发事件后调用观察者的方法
            subject.addLisenter(EnumObserver.ON_ADD,observer,advice);
            subject.add();*/

            //Subject subject = (Subject)new CglbProxy().getInstance(Subject.class);
            SubjectInterface subject = (SubjectInterface)new JdkProxy().getInstance(Subject.class);
            subject.add();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
