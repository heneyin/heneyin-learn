package com.henvealf.learn.designpattern.state;

/**
 * Created by hongliang.yin/Henvealf on 2017/11/14.
 */
public class Context {

    private LiftState liftState;
    //
    public void changeState(LiftState liftState) {
        this.liftState = liftState;
    }

    public void open() {
        liftState.open(this);
    }

    public void close() {
        liftState.close(this);
    }

    public void run() {
        liftState.run(this);
    }

    public void stop() {
        liftState.stop(this);
    }

}
