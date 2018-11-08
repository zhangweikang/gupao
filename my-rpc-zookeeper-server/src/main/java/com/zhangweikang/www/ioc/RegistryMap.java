package com.zhangweikang.www.ioc;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 注册ioc
 *
 * @author ZhangWeiKang
 * @create 2018/6/13
 */
public class RegistryMap {

    private RegistryMap() {
    }

    public ConcurrentHashMap<String,Object> ioc = new ConcurrentHashMap<>();

    public static RegistryMap getInstance(){
        return  RegistryMap1.registryMap;
    }

    public ConcurrentHashMap<String,Object> getIoc(){
        return ioc;
    }

    private static class RegistryMap1{
        private static RegistryMap registryMap = new RegistryMap();

    }
}
