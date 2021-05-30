package com.henvealf.learn.designpattern.state;

/**
 * Created by hongliang.yin/Henvealf on 2017/11/15.
 */
public class LiftRunState extends LiftState {

    private LiftRunState() {}

    private static LiftState state = null;

    public static LiftState getInstance() {
        if (state == null) {
            state = new LiftRunState();
        }
        return state;
    }

    @Override
    public void open(Context context) {
        // no-op
    }

    @Override
    public void close(Context context) {
        //no-op
    }

    @Override
    public void run(Context context) {
        //no-op
    }

    @Override
    public void stop(Context context) {
        super.stop(context);
        changeState(context, LiftStopState.getInstance());
    }
}
