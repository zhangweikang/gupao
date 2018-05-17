package com.zhangweikang.www.strategyPattern;

/**
 * Created by zhangweikang on 2018/4/27.
 */
public class TestMain {

    public static void main(String[] args) {
        String exp = "2+8";
        //ICalculator cal = new Plus();
        ICalculator cal = new Minus();
        int result = cal.calculate(exp);
        System.out.println(result);
    }
}
