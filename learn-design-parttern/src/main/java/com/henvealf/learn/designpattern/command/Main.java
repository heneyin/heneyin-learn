package com.henvealf.learn.designpattern.command;

/**
 * @author hongliang.yin/Henvealf on 2019/1/17
 */
public class Main {

    public static void main(String[] args) {
        Televation televation = new Televation();
        Command c = new OpenCommand(televation);
        c.execute();

        Controller controller = new Controller();
        controller.addCommand(c);
        controller.addCommand(new CloseCommand(televation));
        controller.callAll();


    }


}
