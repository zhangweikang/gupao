package com.zhangweikang.www.rpc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 执行类
 *
 * @author ZhangWeiKang
 * @create 2018/6/13
 */
public class MyHandler implements InvocationHandler {

    private String ip;
    private String port;

    public MyHandler(String ip, String port) {
        this.ip = ip;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        RpcRequest request = new RpcRequest();
        request.setInterfaceName(method.getDeclaringClass().getName());
        request.setMethodName(method.getName());
        request.setMethodArges(args);

        RpcSend rpcSend = new RpcSend(ip, port);
        return rpcSend.send(request);
    }
}
