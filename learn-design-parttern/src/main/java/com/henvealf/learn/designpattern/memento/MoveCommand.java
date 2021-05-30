package com.henvealf.learn.designpattern.memento;

/**
 * 代表一个 改变命令
 * Created by Henvealf on 2017/11/12.
 */
public class MoveCommand {

    private Graphic graphic;
    private Point delta;
    private PointMemento pointMemento = null;

    public MoveCommand(Graphic graphic, Point delta) {
        this.graphic = graphic;
        this.delta = delta;
    }

    public void execute() {
        Point afterPoint = graphic.getPoint();
        pointMemento = new PointMemento(afterPoint);
        graphic.move(delta);
    }

    // 撤销
    public void unExecute() {
        if (pointMemento == null) {
            return;
        }
        Point point = pointMemento.getPoint();
        graphic.move(point);
    }
}

