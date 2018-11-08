package com.zhangweikang.www.rpc;

import java.io.Serializable;

/**
 * Rpc请求信息封装
 *
 * @author ZhangWeiKang
 * @create 2018/6/11
 */
public class RpcRequest implements Serializable{

    private String interfaceName;

    private String methodName;

    private Object[] methodArges;

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getMethodArges() {
        return methodArges;
    }

    public void setMethodArges(Object[] methodArges) {
        this.methodArges = methodArges;
    }
}
