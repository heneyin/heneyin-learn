package com.henvealf.learn.designpattern.state;

/**
 * Created by hongliang.yin/Henvealf on 2017/11/15.
 */
public class LiftStopState  extends LiftState{
    private LiftStopState() {};

    private static LiftState state = null;

    public static LiftState getInstance() {
        if (state == null) {
            state = new LiftStopState();
        }
        return state;
    }

    @Override
    public void open(Context context) {
        super.open(context);
        changeState(context, LiftOpenState.getInstance());
    }

    @Override
    public void close(Context context) {
        //
    }

    @Override
    public void run(Context context) {
        //
    }

    @Override
    public void stop(Context context) {
        //
    }
}
