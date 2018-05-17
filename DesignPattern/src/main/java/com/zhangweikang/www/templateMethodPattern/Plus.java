package com.zhangweikang.www.templateMethodPattern;

/**
 * Created by zhangweikang on 2018/4/27.
 */
public class Plus extends AbstractCalculator {
    @Override
    public int calculate(int num1, int num2) {
        return num1 + num2;
    }
}
