package com.henvealf.learn.designpattern.memento;

/**
 * Created by hongliang.yin/Henvealf on 2017/11/14.
 */
public class Client {

    public static void main(String[] args) {
        Graphic graphic = new Graphic(20, 35);
        Point point = new Point(1,10);
        MoveCommand moveCommand = new MoveCommand(graphic, point);

        Solver solver = new Solver();
        solver.setCommand(moveCommand);
        solver.run();
        System.out.println(graphic.toString());

        solver.notRun();
        System.out.println(graphic.toString());

    }
}
