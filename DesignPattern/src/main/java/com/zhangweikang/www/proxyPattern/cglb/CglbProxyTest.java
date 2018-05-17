package com.zhangweikang.www.proxyPattern.cglb;

/**
 * cglb测试类
 *
 * @author ZhangWeiKang
 * @create 2018/5/17
 */
public class CglbProxyTest {

    public static void main(String[] args) {

        try {
            LiSi lisi = (LiSi)new Cglb58().getInstance(LiSi.class);
            lisi.findJob();

            System.out.println("lisi.getClass() = " + lisi.getClass());

        } catch (Exception e) {

        }
    }
}
