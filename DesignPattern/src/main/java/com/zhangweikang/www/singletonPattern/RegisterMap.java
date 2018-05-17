package com.zhangweikang.www.singletonPattern;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 注册式单例
 *
 * @author ZhangWeiKang
 * @create 2018/5/16
 */
public class RegisterMap {

    private RegisterMap(){}

    private static Map<String,Object> register = new ConcurrentHashMap<String, Object>();

    public static RegisterMap getInstance(String name){
        if (name ==null){
            name = RegisterMap.class.getName();
        }

        if (!register.containsKey(name)){
            register.put(name,new RegisterMap());
        }

        return (RegisterMap)register.get(name);
    }
}
