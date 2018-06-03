package com.zhangweikang.www.serializer;

/**
 * 用户实体
 *
 * @author ZhangWeiKang
 * @create 2018/5/31
 */
public class User extends Person{

    private String name;

    //被transient标记的不会被序列化
    private String address;

    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                "} " + super.toString();
    }
}
