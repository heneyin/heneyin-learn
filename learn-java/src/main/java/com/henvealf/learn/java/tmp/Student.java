package com.henvealf.learn.java.tmp;

/**
 * <p>
 *
 * <p>
 *
 * @author hongliang.yin/Henvealf on 2018/12/20
 */
public class Student {

    private String name;
    private String num;
    private String sex;
    private int english;
    private int math;
    private int javacourse;
    private int score;

    public Student(String name, String num, String sex, int english, int math, int javacourse, int score) {
        this.name = name;
        this.num = num;
        this.sex = sex;
        this.english = english;
        this.math = math;
        this.javacourse = javacourse;
        this.score = score;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name=name;
    }
    public String getNum() {
        return num;
    }
    public void setNum(String num) {
        this.num=num;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex=sex;
    }
    public int getEnglish() {
        return english;
    }
    public void setEnglish(int english) {
        this.english=english;
    }
    public int getMath() {
        return math;
    }
    public void setMath(int math) {
        this.math=math;
    }
    public int getJavacourse() {
        return javacourse;
    }
    public void setJavacourse(int javacourse) {
        this.javacourse=javacourse;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score=score;
    }

}
