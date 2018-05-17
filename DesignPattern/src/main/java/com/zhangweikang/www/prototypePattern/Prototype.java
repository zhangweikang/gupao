package com.zhangweikang.www.prototypePattern;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by zhangweikang on 2018/4/26.
 */
public class Prototype implements Cloneable,Serializable {

    private static final long serialVersionUID = 1L;
    private String string;

    private SerializableObject obj;

    private PrototypeChild prototypeChild;

    /** 浅复制 */
    public Prototype clone() throws CloneNotSupportedException {
        return (Prototype) super.clone();
    }

    /** 深复制 */
    public Prototype deepClone() throws IOException, ClassNotFoundException {

        /* 写入当前对象的二进制流 */
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(this);

        /* 读出二进制流产生的新对象 */
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return (Prototype)ois.readObject();
    }



    public PrototypeChild getPrototypeChild() {
        return prototypeChild;
    }

    public void setPrototypeChild(PrototypeChild prototypeChild) {
        this.prototypeChild = prototypeChild;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public SerializableObject getObj() {
        return obj;
    }

    public void setObj(SerializableObject obj) {
        this.obj = obj;
    }

    class SerializableObject implements Serializable {
        private static final long serialVersionUID = 1L;
    }

    /** 如果该对象被用于序列化，可以保证对象在序列化前后保持一致 */
    /*public Object readResolve() {
        return this;
    }*/
}
