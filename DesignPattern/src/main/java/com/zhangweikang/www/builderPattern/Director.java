package com.zhangweikang.www.builderPattern;

/**
 * 实施人员
 * Created by zhangweikang on 2018/4/26.
 */
public class Director {
    //指挥装机人员组装电脑
    public void Construct(Builder builder) {

        builder.BuildCPU();
        builder.BuildMainboard();
        builder.BuildHD();
    }
}
