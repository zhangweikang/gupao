package com.zhangweikang.www.mementoPattern;

/**
 * Created by zhangweikang on 2018/5/3.
 */
public class Memento {
    private String value;

    public Memento(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
