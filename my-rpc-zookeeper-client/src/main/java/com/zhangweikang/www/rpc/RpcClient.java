package com.zhangweikang.www.rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

/**
 * RpcClient
 *
 * @author ZhangWeiKang
 * @create 2018/6/13
 */
public class RpcClient<T> implements InvocationHandler {

    private Class<T> clzss;
    private Socket socket;

    public T getInstance(Class<T> clzss, Socket socket) {
        this.clzss = clzss;
        this.socket = socket;
        return (T) Proxy.newProxyInstance(clzss.getClassLoader(), new Class[]{clzss}, this);
    }

    public Object invoke(Object proxy, Method method, Object[] args){

        try {
            RpcRequest request = new RpcRequest();
            request.setInterfaceName(clzss.getName());
            request.setMethodName(method.getName());
            request.setMethodArges(args);

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(request);
            objectOutputStream.flush();

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Object o = objectInputStream.readObject();

            objectInputStream.close();
            objectOutputStream.close();

            return o;
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
