package com.zhangweikang.www.proxyPattern.statices;

/**
 * 测试类
 *
 * @author ZhangWeiKang
 * @create 2018/5/17
 */
public class StaticeTestMain {

    public static void main(String[] args) {

        Father father = new Father(new Son());

        father.findLove();
    }
}
