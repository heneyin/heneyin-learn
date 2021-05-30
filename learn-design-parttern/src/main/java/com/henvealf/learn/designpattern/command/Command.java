package com.henvealf.learn.designpattern.command;

/**
 * 遥控器发出的命令的抽象。
 *
 *
 * @author hongliang.yin/Henvealf on 2019/1/17
 */
public abstract class Command {

    protected Televation televation;

    void setTelevation(Televation televation) {
        this.televation = televation;
    }

    public abstract boolean execute();

}
