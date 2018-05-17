package com.zhangweikang.www.proxyPattern.jdk;

import com.zhangweikang.www.proxyPattern.Person;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;

/**
 * @author ZhangWeiKang
 * @create 2018/5/17
 */
public class JdkProxyTest {

    public static void main(String[] args) {
        try {
            Person instance = (Person)new JDKMeipo().getInstance(new ZhangSan());
            instance.findJob();
            System.out.println("instace = " + instance.getClass());

            byte[] $Proxy0s = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{Person.class});

            FileOutputStream fos = new FileOutputStream("D:\\$Proxy0.class");
            fos.write($Proxy0s);

            fos.close();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }
}
