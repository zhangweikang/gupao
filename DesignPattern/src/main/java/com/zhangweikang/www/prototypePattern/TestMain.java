package com.zhangweikang.www.prototypePattern;

/**
 * 原型模式Test
 *
 * @author ZhangWeiKang
 * @create 2018/5/16
 */
public class TestMain {

    public static void main(String[] args) {
        Prototype prototype = new Prototype();

        prototype.setString("aaaaa");

        prototype.setPrototypeChild(new PrototypeChild());

        System.out.println("prototype = " + prototype);
        try {
            //Prototype clone = prototype.clone();
            Prototype clone = prototype.deepClone();

            System.out.println("clone = " + clone);
            System.out.println(prototype.getPrototypeChild() == clone.getPrototypeChild());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
