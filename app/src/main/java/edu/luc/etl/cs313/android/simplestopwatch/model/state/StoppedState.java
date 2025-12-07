package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;

class StoppedState implements StopwatchState {
    //The new timer stopped state

    public StoppedState(final StopwatchSMStateView sm) {
        this.sm = sm;
    }

    private final StopwatchSMStateView sm;

    @Override
    public void onStartStop() {
        //Button pressed = numberDisplayed++;
        sm.actionInc();
        sm.toLapRunningState(); //LapRunningState will now become the  after 3 seconds,
        sm.actionStart();
    }

    @Override
    public void onLapReset() { //not relevent
        sm.actionReset();
        sm.toStoppedState();
    }

    @Override
    public void onTick() {
        //throw new UnsupportedOperationException("onTick");
    }

    @Override
    public void updateView() {
        sm.updateUIRuntime();
    }

    @Override
    public int getId() {
        return R.string.STOPPED;
    }
}
