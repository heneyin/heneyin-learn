package com.henvealf.learn.java.innerclass.controller;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理并触发事件的实际控制框架
 * Created by Henvealf on 2016/9/7.
 */
public class Controller {
    //
    private List<Event> eventList = new ArrayList<>();
    public void addEvent(Event c) {
        eventList.add(c);
    }

    /**
     * 开始运行控制框架
     */
    public void run(){
        while(eventList.size() > 0) {
            for (Event e:  new ArrayList<Event>(eventList))
                if(e.ready()) {
                    System.out.println(e);
                    e.action();
                    eventList.remove(e);
                }
        }
    }
}
