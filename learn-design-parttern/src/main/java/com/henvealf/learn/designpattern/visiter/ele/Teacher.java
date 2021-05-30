package com.henvealf.learn.designpattern.visiter.ele;


import com.henvealf.learn.designpattern.visiter.visitor.ActionVisitor;

/**
 * 一个老师
 * Created by hongliang.yin/Henvealf on 2017/11/16.
 */
public interface Teacher {
    public void action(ActionVisitor actionVisitors);
}
