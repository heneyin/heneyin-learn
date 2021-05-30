package com.henvealf.learn.designpattern.state;

/**
 * Created by hongliang.yin/Henvealf on 2017/11/14.
 */
public class LiftOpenState extends LiftState {

    private LiftOpenState() {};

    private static LiftState state = null;

    public static LiftState getInstance() {
        if (state == null) {
            state = new LiftOpenState();
        }
        return state;
    }

    @Override
    public void open(Context context) {
        // no-op
    }

    @Override
    public void close(Context context) {
        super.close(context);
        changeState(context, LiftCloseState.getInstance());
    }

    @Override
    public void run(Context context) {
        // no-op
    }

    @Override
    public void stop(Context context) {
        // no-op
    }
}
