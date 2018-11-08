package com.zhangweikang.www.rpc.client;

import com.zhangweikang.www.rpc.interfaces.IHelloService;

import java.rmi.Naming;

/**
 * ClientTest
 *
 * @author ZhangWeiKang
 * @create 2018/6/3
 */
public class ClientTest {

    public static void main(String[] args) {
        try {
            IHelloService helloService = (IHelloService)Naming.lookup("rmi://localhost:1099/hello");
            helloService.sayHello("Tom");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
