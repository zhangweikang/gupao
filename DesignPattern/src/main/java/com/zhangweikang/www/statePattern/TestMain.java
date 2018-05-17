package com.zhangweikang.www.statePattern;

/**
 * Created by zhangweikang on 2018/5/3.
 */
public class TestMain {
    public static void main(String[] args) {
        State state = new State();
        Context context = new Context(state);

        //设置第一种状态
        state.setValue("state1");
        context.method();

        //设置第二种状态
        state.setValue("state2");
        context.method();
    }
}
