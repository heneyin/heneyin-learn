package com.henvealf.learn.designpattern.memento;

/**
 * Created by Henvealf on 2017/11/14.
 */
public class Solver {

    MoveCommand command = null;

    public void setCommand(MoveCommand command) {
        this.command = command;
    }

    public void run() {
        command.execute();
    }

    public void notRun() {
        command.unExecute();
    }

}
