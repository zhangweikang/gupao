package com.zhangweikang.www.singletonPattern;

/**
 * 内部类单例方式
 *
 * 解决了:饿汉式浪费资源问题
 *       懒汉式低性能问题
 * @author ZhangWeiKang
 * @create 2018/5/16
 */
public class SingletonInternalClass {

    private SingletonInternalClass(){}

    /**
     * static :保证单例空间共享
     * final :保证方法不能被重写
     * @return SingletonInternalClass
     */
    public static final SingletonInternalClass getInstance(){
        return InternalClass.internalClass;
    }

    /**
     * 当类被使用的时候,内部类会先被加载
     */
    private static class InternalClass{
        private static final SingletonInternalClass internalClass = new SingletonInternalClass();
    }
}
