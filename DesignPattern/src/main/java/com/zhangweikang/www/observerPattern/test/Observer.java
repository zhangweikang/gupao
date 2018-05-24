package com.zhangweikang.www.observerPattern.test;

import com.zhangweikang.www.observerPattern.event.Event;

/**
 * observer
 *
 * @author ZhangWeiKang
 * @create 2018/5/23
 */
public class Observer {

    public void advice(Event event){
        System.out.println(" ----触发时间打印日志---- \r\n" + event);
    }
}
