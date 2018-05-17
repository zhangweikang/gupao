package com.zhangweikang.www.singletonPattern;

/**
 * 懒汉式
 * 线程不安全
 * Created by zhangweikang on 2018/4/25.
 */
public class Singleton3 {

    private static Singleton3 singleton = null;

    /**
     * 线程安全
     *
     * 从代码层面上看,下面代码没有安全问题,其实是有问题的
     *
     * 在jvm处理,赋值和实例化的时候是两个步骤,先后顺序是不确定的,所以还是会有异常
     *
     * 1.A,B两个线程同时调用getSingleton2获取实例的方法
     * 2.A,B同时进入了同一个singleton == null 的地方,synchronized 锁住 排队
     * ,A先进来synchronized块,singleton=null,jvm为singleton开辟一块空白的空间,先为singleton赋值,这个时候如果还没有实例化singleton
     * A出去了synchronized块,B进来synchronized块
     * 3.B发现singleton不为空,拿着singleton去使用,发现singleton没有实例化,这个时候就会报错了
     */
    Singleton3 getSingleton(){
        if (singleton == null) {
            synchronized (singleton){
                if (singleton == null){
                    singleton = new Singleton3();
                }
            }
        }
        return singleton;
    }
}
