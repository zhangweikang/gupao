package com.zhangweikang.www.rpc.interfaces.impl;

import com.zhangweikang.www.rpc.interfaces.IHelloService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author ZhangWeiKang
 * @create 2018/6/3
 */
public class HelloServiceImpl extends UnicastRemoteObject implements IHelloService {

    /** indicate compatibility with JDK 1.1.x version of class */
    private static final long serialVersionUID = 1L;

    public HelloServiceImpl() throws RemoteException {
        super();
    }

    public void sayHello(String name) throws RemoteException {
        System.out.println("Hello," + name);
    }
}
