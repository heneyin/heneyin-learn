package com.henvealf.learn.designpattern.visiter.ele;

import com.henvealf.learn.designpattern.visiter.visitor.ActionVisitor;

/**
 * 语文老师
 * Created by hongliang.yin/Henvealf on 2017/11/16.
 */
public class ChineseTeacher implements Teacher {

    private String text = "真的猛士，";

    @Override
    public void action(ActionVisitor actionVisitors) {
        actionVisitors.readTheText(this);
    }

    public String getText() {
        return text;
    }
}
