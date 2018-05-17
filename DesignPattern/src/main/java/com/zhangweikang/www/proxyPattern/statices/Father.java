package com.zhangweikang.www.proxyPattern.statices;

import com.zhangweikang.www.proxyPattern.Person;

/**
 * 父亲
 *
 * @author ZhangWeiKang
 * @create 2018/5/17
 */
public class Father {

    private Person person;

    public Father(Person person){
        this.person = person;
    }

    public void findLove(){
        System.out.println(" 过滤筛选儿子条件 ");

        person.findLove();

        System.out.println(" 挑选完成,后面办事 ");
    }
}
