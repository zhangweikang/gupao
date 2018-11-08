package com.zhangweikang.www.rpc.server;

import com.zhangweikang.www.rpc.interfaces.IHelloService;
import com.zhangweikang.www.rpc.interfaces.impl.HelloServiceImpl;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 * ServerTest
 *
 * @author ZhangWeiKang
 * @create 2018/6/3
 */
public class ServerTest {

    public static void main(String[] args) {

        try {
            IHelloService helloService = new HelloServiceImpl();

            LocateRegistry.createRegistry(1099);
            Naming.rebind("rmi://127.0.0.1:1099/hello",helloService);

            System.out.println(" Server服务启动成功 ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
