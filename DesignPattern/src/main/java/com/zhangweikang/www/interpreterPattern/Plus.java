package com.zhangweikang.www.interpreterPattern;

/**
 * Created by zhangweikang on 2018/5/3.
 */
public class Plus implements Expression {
    @Override
    public int interpret(Context context) {
        return context.getNum1()+context.getNum2();
    }
}
