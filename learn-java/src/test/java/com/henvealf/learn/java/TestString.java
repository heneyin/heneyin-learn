package com.henvealf.learn.java;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 如果是直接赋值的字符串对象，会存放到运行时常量池中，并返回在常量池中的引用。
 *
 * 如果使用 new String() 创建的对象，会在堆中创建一个对象，对象中的元素引用常量池中的对象。
 *
 * 使用 intern 方法寻找在字符串在常量池中的引用，不存在就放入。
 *
 * 编译器会将多个用 + 连接的字符串优化为一个字符串。
 * <p>
 *
 * @author hongliang.yin/Henvealf on 2019-03-23
 */
public class TestString {

    @Test
    public void testImmediate() {
        String a = "abc";
        String b = "a" + "b" + "c";
        System.out.println(a);
        System.out.println(b);
        System.out.println(a == b);
    }

    @Test
    public void testNew() {
        String a1 = new String("a");
        String a2 = new String("a");
        System.out.println(a1 == a2);
    }

    @Test
    public void testMix() {
        String a1 = "a";
        String a2 = new String("a");
        System.out.println(a1 == a2);
    }

    @Test
    public void testCharArray(){
        char[] c1 = new char[]{'1', '2', '3'};
        char[] c2 = new char[]{'1', '2', '3'};
        System.out.println(c1 == c2);

        char[] c3 = c1;
        System.out.println(c1 == c3);
        char a = ' ';
        System.out.println(a == ' ');
    }

    @Test
    public void testA() {
        int nameLength = lengthOfLastWord("feiyue");
        System.out.println(nameLength);
    }

    public int lengthOfLastWord(String name) {
//        int i = s.length() - 1;
//        for(;i > -1; --i){
//            if (s.charAt(i) == ' ') {
//                return s.length() - i - 1;
//            }
//        }
        return name.length();
    }

    @Test
    public void hello() {
        System.out.print("hello fei\n");
    }

}
