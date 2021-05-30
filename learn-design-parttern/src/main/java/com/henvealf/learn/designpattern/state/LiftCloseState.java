package com.henvealf.learn.designpattern.state;

/**
 * Created by hongliang.yin/Henvealf on 2017/11/15.
 */
public class LiftCloseState extends LiftState{

    private LiftCloseState() {}
    private static LiftState state = null;

    public static LiftState getInstance() {
        if (state == null) {
            state = new LiftCloseState();
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
        // no-op
    }

    @Override
    public void run(Context context) {
        super.run(context);
        changeState(context, LiftRunState.getInstance());
    }

    @Override
    public void stop(Context context) {
        // no-op
    }
}
