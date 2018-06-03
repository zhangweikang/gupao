package com.zhangweikang.www.serializer;

/**
 * 测试类
 *
 * @author ZhangWeiKang
 * @create 2018/5/31
 */
public class TestMain {

    public static void main(String[] args) {
        //ISerializer serializer = new JavaSerializer();
        ISerializer serializer = new ProtosbufSerializer();
        //ISerializer serializer = new KroySerializer();
        User user = new User();
        user.setAddress("测试");
        user.setAge(18);
        user.setName("小明");
        user.setSex("男");

        /*List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");*/

        long beginTime = System.currentTimeMillis();
        System.out.println("序列化,begin = " + beginTime);
        byte[] serializer1 = serializer.serializer(user);

        long endTime = System.currentTimeMillis();
        System.out.println("serializer1 = " + serializer1.length + ",序列化时间,s = " + (endTime-beginTime));

        long deBeginTime = System.currentTimeMillis();
        System.out.println("反序列化,begin = " + deBeginTime);
        User deserializer = serializer.deserializer(serializer1, User.class);
        long deEendTime = System.currentTimeMillis();
        System.out.println("deserializer = " + deserializer + "反序列化耗时,s = " + (deEendTime-deBeginTime));


        System.out.println(" 总时长 , s = " + (deEendTime - beginTime));
    }
}
