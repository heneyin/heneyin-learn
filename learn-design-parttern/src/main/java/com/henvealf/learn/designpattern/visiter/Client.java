package com.henvealf.learn.designpattern.visiter;


import com.henvealf.learn.designpattern.visiter.visitor.ActionVisitor;
import com.henvealf.learn.designpattern.visiter.visitor.BadActionVisitor;

/**
 * Created by hongliang.yin/Henvealf on 2017/11/16.
 */
public class Client {

    public static void main(String[] args) {
        ActionVisitor visitor = new BadActionVisitor();
        School school = new School();
        school.beginTeach(visitor);
    }
}
