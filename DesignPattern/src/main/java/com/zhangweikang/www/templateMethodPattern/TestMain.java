package com.zhangweikang.www.templateMethodPattern;

/**
 * Created by zhangweikang on 2018/4/27.
 */
public class TestMain {

    public static void main(String[] args) {
        String exp = "8+8";
        AbstractCalculator cal = new Plus();
        int result = cal.calculate(exp, "\\+");
        System.out.println(result);
    }
}
