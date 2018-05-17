package com.zhangweikang.www.singletonPattern;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.CountDownLatch;

/**
 * 测试单例
 *
 * @author ZhangWeiKang
 * @create 2018/5/16
 */
public class TestMain {

    public static void main(String[] args) {
        //threadr();//并发模拟

        //serializable();//序列化,反序列化单例模拟
    }

    private static void threadr(){
        int count = 500;
        final CountDownLatch latch = new CountDownLatch(count);

        for (int i = 0; i < count; i++) {
            new Thread(){
                @Override
                public void run() {
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //Singleton.getInstance();//恶汉式
                    Singleton2 instance = Singleton2.getInstance();//懒汉式

                    System.out.println("time = " +System.currentTimeMillis()+";singleton = "+ instance);
                }
            }.start();

            latch.countDown();
        }
    }

    private static void serializable(){
        try {
            SerializableSingleton s1;
            SerializableSingleton s2 = SerializableSingleton.getInstance();

            FileOutputStream fos = new FileOutputStream("singleton.obj");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(s2);

            oos.close();
            fos.close();

            FileInputStream fis = new FileInputStream("singleton.obj");
            ObjectInputStream ois = new ObjectInputStream(fis);

            s1 = (SerializableSingleton)ois.readObject();
            ois.close();
            fis.close();

            System.out.println("s1 = " + s1);
            System.out.println("s2 = " + s2);
            System.out.println("s1 == s2 ? : " + (s1 == s2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
