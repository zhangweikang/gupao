package com.zhangweikang.www.builderPattern;

/**
 * 电脑组装者
 * Created by zhangweikang on 2018/4/26.
 */
public class ConcreteBuilder extends Builder {

    /** 创建电脑实例 */
    Computer computer = new Computer();

    @Override
    public void BuildCPU() {
        computer.Add("安装CPU");
    }

    @Override
    public void BuildMainboard() {
        computer.Add("组装主板");
    }

    @Override
    public void BuildHD() {
        computer.Add("组装硬盘");
    }

    @Override
    public Computer GetComputer() {
        return computer;
    }
}
