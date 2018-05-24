package com.zhangweikang.www.observerPattern.event;

import java.lang.reflect.Method;

/**
 * event
 *
 * @author ZhangWeiKang
 * @create 2018/5/23
 */
public class Event {

    //时间源
    private Object source;

    //通知目标
    private Object target;

    //回调方法
    private Method callBack;

    //调用方
    private String trigger;

    private Long time;

    public Event(Object target, Method callBack) {
        this.target = target;
        this.callBack = callBack;
    }

    public Object getSource() {
        return source;
    }

    Event setSource(Object source) {
        this.source = source;
        return this;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Method getCallBack() {
        return callBack;
    }

    public void setCallBack(Method callBack) {
        this.callBack = callBack;
    }

    public String getTrigger() {
        return trigger;
    }

    public Long getTime() {
        return time;
    }

    Event setTime(Long time) {
        this.time = time;
        return this;
    }

    Event setTrigger(String trigger) {
        this.trigger = trigger;
        return this;
    }

    @Override
    public String toString() {
        return "Event{\r\n" +
                "\tsource=" + source + "\r\n"+
                "\ttarget=" + target + "\r\n"+
                "\tcallBack=" + callBack + "\r\n"+
                "\ttrigger='" + trigger + '\'' + "\r\n"+
                '}';
    }
}
