package com.henvealf.learn.java.typeinfo;

/**
 * Class对象，用于存储类的类型信息,对应于.class文件
 * <br>可以使用Class.forName()来获取，使用此方法可以在你没有获得恰当的Class对象时获取他的Class
 * Created by Henvealf on 2016/9/8.
 */

class Candy {
    static {
        System.out.println("Loading Candy");
    }
}

class Gum {
    static {
        System.out.println("Loading Gum");
    }
}

class Cookie {

    static {
        System.out.println("Loading Cookie");
    }
}

/**
 * Class.forName会返回一个Class对象的引用。
 */
public class SweetShop {
    public static void main(String[] args) {
        System.out.println("inside main");
        new Candy();
        System.out.println("After creating Candy");
        try {
            // 这里需要指定包名
            Class.forName("com.henvealf.learn.typeinfo.Gum");
        } catch (ClassNotFoundException e) {
            System.out.println("could't not found");
        }
        System.out.println("After Class.forName(Gum)");
        new Cookie();
        System.out.println("After creating Cookies");
    }


}
