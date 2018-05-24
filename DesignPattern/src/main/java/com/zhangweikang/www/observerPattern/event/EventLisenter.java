package com.zhangweikang.www.observerPattern.event;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 监听器
 *
 * @author ZhangWeiKang
 * @create 2018/5/23
 */
public class EventLisenter {

    Map<Enum,Event> eventMap = new HashMap<>();

    public void addLisenter(Enum eventType ,Object target ,Method method){
        eventMap.put(eventType,new Event(target,method));
    }

    //触发
    private void trigger(Event event){
        event.setSource(this);
        event.setTime(System.currentTimeMillis());

        try {
            event.getCallBack().invoke(event.getTarget(),event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void trigger(Enum enums){
        if (!eventMap.containsKey(enums)){
            return ;
        }
        trigger(this.eventMap.get(enums).setTrigger(enums.toString()));
    }
}
