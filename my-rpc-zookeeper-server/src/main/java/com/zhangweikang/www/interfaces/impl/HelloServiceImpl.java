package com.zhangweikang.www.interfaces.impl;

import com.zhangweikang.www.interfaces.IHelloService;

/**
 * @author ZhangWeiKang
 * @create 2018/6/11
 */
public class HelloServiceImpl implements IHelloService {
    public String sayHello(String name) {
        return name + ",say hello";
    }
}
