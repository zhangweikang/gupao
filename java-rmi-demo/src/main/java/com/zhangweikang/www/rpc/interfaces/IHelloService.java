package com.zhangweikang.www.rpc.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * helloservice
 *
 * @author ZhangWeiKang
 * @create 2018/6/3
 */
public interface IHelloService extends Remote {

    void sayHello(String name) throws RemoteException;

}
