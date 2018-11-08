package com.zhangweikang.www.ioc;

import java.util.concurrent.ConcurrentHashMap;

/**
 * BeanFactory
 *
 * @author ZhangWeiKang
 * @create 2018/6/13
 */
public class BeanFactory {

    private ConcurrentHashMap<String, Object> ioc = new ConcurrentHashMap<>();

    public static BeanFactory getInstance() {
        return $BeanFactory.beanFactry;
    }

    public Object getBean(String packageName) {
        return getInstance().ioc.get(packageName);
    }

    public synchronized void registry(Class<?> clazz, Object server) {
        String packageClassName = clazz.getName();
        ioc.put(packageClassName, server);

        System.out.println(packageClassName + "-> 容器注册成功");
    }

    private static class $BeanFactory {
        public static BeanFactory beanFactry = new BeanFactory();
    }
}
