package com.zhangweikang.www.compositePattern;

/**
 * Created by zhangweikang on 2018/4/27.
 */
public class TestMain {

    public static void main(String[] args) {
        Tree tree = new Tree("A");
        TreeNode nodeB = new TreeNode("B");
        TreeNode nodeC = new TreeNode("C");

        nodeB.add(nodeC);
        tree.root.add(nodeB);
        System.out.println("build the tree finished!");
    }
}
