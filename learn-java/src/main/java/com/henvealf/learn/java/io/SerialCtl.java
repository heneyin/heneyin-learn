package com.henvealf.learn.java.io;

import java.io.*;

/**
 * Externalizable的替代方法。
 * Created by Henvealf on 2016/9/6.
 */
public class SerialCtl implements Serializable {
    private String a;
    private transient String b;
    public SerialCtl(String aa, String bb) {
        this.a = "Not transient " +  aa;
        this.b = "transient " +  bb;
    }

    public String toString() {
        return a + "\n" + b;
    }

    // 在Serializable的实现类里面添加两个私有private 方法，属意这里不是重写或者实现。
    // 这两个方法的实际调用者是ObjectOutputStream与ObjectInputStream这两个类。
    // 当你调用ObjectOutputStream的writeObject()与ObjectInputStream的readObject()时，
    // 就实际调用了这两个私有方法(使用了反射机制)，来完成你想要的序列化操作。
    private void writeObject(ObjectOutputStream stream) throws IOException {
        // 调用默认的序列化操作
        stream.defaultWriteObject();
        // 此处为自定义的序列化操作
        stream.writeObject(b);
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.b = (String) stream.readObject();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SerialCtl sc =  new SerialCtl("test1","test2");
        System.out.println("Before : \n" + sc);
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        ObjectOutputStream o = new ObjectOutputStream(buf);
        o.writeObject(sc);

        //现在反序列回来
        ObjectInputStream in = new ObjectInputStream(
          new ByteArrayInputStream(buf.toByteArray())
        );
        SerialCtl cs2 = (SerialCtl) in.readObject()                     ;
        System.out.println("After: \n " + cs2);
    }

}
