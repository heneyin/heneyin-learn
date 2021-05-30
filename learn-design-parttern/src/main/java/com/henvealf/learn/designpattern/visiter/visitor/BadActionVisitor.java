package com.henvealf.learn.designpattern.visiter.visitor;


import com.henvealf.learn.designpattern.visiter.ele.ChineseTeacher;
import com.henvealf.learn.designpattern.visiter.ele.MathTeacher;

/**
 * Created by hongliang.yin/Henvealf on 2017/11/16.
 */
public class BadActionVisitor implements ActionVisitor {
    @Override
    public int getMathResult(MathTeacher teacher) {
        return 2333;
    }

    @Override
    public void readTheText(ChineseTeacher teacher) {
        System.out.println("这个字念什么来...");
    }
}
