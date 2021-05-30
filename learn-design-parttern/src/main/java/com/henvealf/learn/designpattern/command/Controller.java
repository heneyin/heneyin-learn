package com.henvealf.learn.designpattern.command;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 电视遥控器，
 * 命令的发送者。
 * @author hongliang.yin/Henvealf on 2019/1/17
 */
public class Controller {

    private LinkedList<Command> cmds = new LinkedList<>();
    private Televation televation;

    public Controller() {}

    public Controller(Televation televation) {
        this.televation = televation;
    }

    public void addCommand(Command c) {
        if (c.televation == null && televation != null){
            c.setTelevation(televation);
        } else if (c.televation == null && televation == null) {
            throw new RuntimeException("command and controller both not known the televation");
        }
        cmds.add(c);
    }

    public void call() {
        Command command = cmds.poll();
        if (command != null)
            command.execute();
    }

    public void callAll() {
        for (Command cmd : cmds) {
            cmd.execute();
        }
    }

}
