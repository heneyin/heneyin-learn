package com.henvealf.learn.designpattern.state;

/**
 * Created by hongliang.yin/Henvealf on 2017/11/15.
 */
public class Client {

    public static void main(String[] args) {
        Context context = new Context();
        context.changeState(LiftRunState.getInstance());
        context.run();

        context.stop();
        context.open();
        context.close();
        context.run();
        context.run();

        context.open();
    }
}
