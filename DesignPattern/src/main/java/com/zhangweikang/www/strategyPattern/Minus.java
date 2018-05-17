package com.zhangweikang.www.strategyPattern;

/**
 * Created by zhangweikang on 2018/4/27.
 */
public class Minus extends AbstractCalculator implements ICalculator {
    public int calculate(String exp) {
        int arrayInt[] = split(exp,"-");
        return arrayInt[0]-arrayInt[1];
    }
}
