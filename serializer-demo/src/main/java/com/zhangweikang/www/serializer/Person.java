package com.zhangweikang.www.serializer;

/**
 * 父类
 *
 * @author ZhangWeiKang
 * @create 2018/5/31
 */
public class Person{

    private String sex;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Person{" +
                "sex='" + sex + '\'' +
                '}';
    }
}
