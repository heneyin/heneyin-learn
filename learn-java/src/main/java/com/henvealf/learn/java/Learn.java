package com.henvealf.learn.java;

/**
 * @author hongliang.yin/Henvealf on 2018/10/2
 */
public class Learn {

    public static void main(String[] args) {
        learnFor();
    }

    public static void learnFor() {
        int[] lll = {12, 20, 23, 43,78, 100, 203, -1, 23, 131, 1234, 2213, 88, 98, 67};

        for (int  i = 0; i < lll.length ; i = i + 1) {
            String result = ifelse(lll[i]);
            System.out.println(i + "号同学的成绩是：" + lll[i] + "， ta 的评级为： " + result);
            if (result.equals("太厉害了")) {
                System.out.println("请回火星去。");
            }
        }

    }

    public static void learnWhile() {

    }

    // void 没有返回
    public static String ifelse(int grade) {
        if (grade > 100) {
            return "太厉害了";
        } else if (grade >= 90) {
            return "优秀";
        } else if (grade >= 60) {
            return "良";
        } else if (grade >= 0){
            return "不及格";
        } else {
            return "成绩不正常";
        }
    }

    public static int 勾股定义(int length1, int length2) {
        int r = length1 * length1 + length2 * length2;
        return r;
    }

}
