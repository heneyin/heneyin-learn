package com.henvealf.learn.designpattern.command;

/**
 * 打开电视的命令
 * @author hongliang.yin/Henvealf on 2019/1/17
 */
public class OpenCommand extends Command{

    public OpenCommand() {}

    public OpenCommand(Televation televation) {
        this.televation = televation;
    }

    @Override
    public boolean execute() {
        televation.open();
        return true;
    }

}
