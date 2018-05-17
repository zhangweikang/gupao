package com.zhangweikang.www.proxyPattern.jdk;

import com.zhangweikang.www.proxyPattern.Person;

/**
 * 张三
 *
 * @author ZhangWeiKang
 * @create 2018/5/17
 */
public class ZhangSan implements Person {

    public void findLove(){
        System.out.println(" 长得漂亮 ");
    }

    @Override
    public void findJob() {
        System.out.println("20-50k");
    }
}
