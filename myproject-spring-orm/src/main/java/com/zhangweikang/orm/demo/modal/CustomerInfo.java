package com.zhangweikang.orm.demo.modal;

import javax.persistence.Table;

/**
 * 实体
 *
 * @author ZhangWeiKang
 * @create 2018/5/11
 */
@Table(name = "customer_info")
public class CustomerInfo {

    private Long id;

    private String name;

    private String addr;

    private Integer age;

    private String realName;

    private Integer nikeAge;

    @Override
    public String toString() {
        return "CustomerInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", addr='" + addr + '\'' +
                ", age=" + age +
                ", realName='" + realName + '\'' +
                ", nikeAge=" + nikeAge +
                '}';
    }
}
