package com.henvealf.learn.designpattern.memento;

/**
 * 代表图像的具体位置
 * Created by Henvealf on 2017/11/12.
 */
public class Point implements Cloneable {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.y = y;
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    @Override
    protected Point clone() throws CloneNotSupportedException {
        super.clone();
        return new Point(x, y);
    }

    public Point getClone() {
        try {
            return this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}

