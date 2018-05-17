package com.zhangweikang.www.compositePattern;

/**
 * Created by zhangweikang on 2018/4/27.
 */
public class Tree {

    TreeNode root = null;

    public Tree(String name) {
        root = new TreeNode(name);
    }
}
