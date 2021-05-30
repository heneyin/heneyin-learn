package com.henvealf.learn.designpattern.visiter;


import com.henvealf.learn.designpattern.visiter.ele.ChineseTeacher;
import com.henvealf.learn.designpattern.visiter.ele.MathTeacher;
import com.henvealf.learn.designpattern.visiter.ele.Teacher;
import com.henvealf.learn.designpattern.visiter.visitor.ActionVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hongliang.yin/Henvealf on 2017/11/16.
 */
public class School {

    private List<Teacher> teacherList;

    public School() {
        this.teacherList = new ArrayList<>();
        Teacher mathTeacher = new MathTeacher(1, 1);
        teacherList.add(mathTeacher);
        Teacher chineseTeacher = new ChineseTeacher();
        teacherList.add(chineseTeacher);
    }

    public void beginTeach(ActionVisitor visitor) {
        for (Teacher teacher: teacherList) {
            teacher.action(visitor);
        }
    }

}
