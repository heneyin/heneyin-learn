package com.henvealf.learn.designpattern.visiter.ele;


import com.henvealf.learn.designpattern.visiter.visitor.ActionVisitor;

/**
 * 数学老师
 * Created by hongliang.yin/Henvealf on 2017/11/16.
 */
public class MathTeacher implements Teacher {

    public int x = 0;
    public int y = 0;

    public MathTeacher(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void action(ActionVisitor actionVisitors) {
        int result = actionVisitors.getMathResult(this);
        System.out.println("嗯。"+ x + "+" + y + "=" + result);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
