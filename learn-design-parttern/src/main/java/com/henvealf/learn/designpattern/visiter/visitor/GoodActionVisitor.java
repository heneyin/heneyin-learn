package com.henvealf.learn.designpattern.visiter.visitor;


import com.henvealf.learn.designpattern.visiter.ele.ChineseTeacher;
import com.henvealf.learn.designpattern.visiter.ele.MathTeacher;

/**
 * 一个好的 Visitor
 * Created by hongliang.yin/Henvealf on 2017/11/16.
 */
public class GoodActionVisitor implements ActionVisitor {
    @Override
    public int getMathResult(MathTeacher teacher) {
        return teacher.getX() + teacher.getY();
    }

    @Override
    public void readTheText(ChineseTeacher teacher) {
        System.out.println(teacher.getText());
    }
}
