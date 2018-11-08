package com.zhangweikang.www.rpc;

import java.lang.reflect.Proxy;

/**
 * Client代理类
 *
 * @author ZhangWeiKang
 * @create 2018/6/13
 */
public class ClientProxy {

    public <T>T getInstance(Class<T> clzss, String ip,String port) {
        return (T) Proxy.newProxyInstance(clzss.getClassLoader(), new Class[]{clzss}, new MyHandler(ip,port));
    }

}
