package com.zhangweikang.orm.demo.modal;

import javax.persistence.Table;

/**
 * 测试类User
 *
 * @author ZhangWeiKang
 * @create 2018/5/14
 */
@Table(name = "user")
public class User {

    private Long id;

    private String realName;

    private Integer myAge;

    private String myAddres;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", realName='" + realName + '\'' +
                ", myAge=" + myAge +
                ", myAddres='" + myAddres + '\'' +
                '}';
    }
}
