package com.henvealf.learn.designpattern.state;

/**
 * Created by hongliang.yin/Henvealf on 2017/11/14.
 */
public abstract class LiftState {
    public void open(Context context) {
        System.out.println("open");
    }
    public void close(Context context) {
        System.out.println("close");
    }
    public void run(Context context) {
        System.out.println("run");
    }
    public void stop(Context context){
        System.out.println("stop");
    }
    public void changeState(Context context, LiftState liftState) {
        context.changeState(liftState);
    }
}
