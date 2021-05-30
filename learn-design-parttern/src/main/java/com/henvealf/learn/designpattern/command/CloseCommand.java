package com.henvealf.learn.designpattern.command;

/**
 * @author hongliang.yin/Henvealf on 2019/1/17
 */
public class CloseCommand extends Command {

    public CloseCommand() {}

    public CloseCommand(Televation televation) {
        this.televation = televation;
    }

    @Override
    public boolean execute() {
        televation.close();
        return true;
    }

}
