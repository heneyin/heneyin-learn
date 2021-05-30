package com.henvealf.learn.designpattern.memento;

/**
 * 一个备忘录
 * Created by Henvealf on 2017/11/12.
 */
public class PointMemento {

    private Point point;

    public PointMemento(Point point) {
        this.point = point.getClone();
    }

    public Point getPoint() {
        return point;
    }
}

