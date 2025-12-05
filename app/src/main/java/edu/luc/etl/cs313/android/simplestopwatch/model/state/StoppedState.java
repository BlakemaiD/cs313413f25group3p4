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
        //when the timer is on stop, it is increased when user presses the button
        sm.actionInc();
        //sm.toRunningState();
        sm.toLapRunningState(); //LapRunningState will now become the count down after 3 seconds, to repourpose the file
    }

    @Override
    public void onLapReset() { //not relevent
        sm.actionReset();
        sm.toStoppedState();
    }

    @Override
    public void onTick() {
        throw new UnsupportedOperationException("onTick");
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
