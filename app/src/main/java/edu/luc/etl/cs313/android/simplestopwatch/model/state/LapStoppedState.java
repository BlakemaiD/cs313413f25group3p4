package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;

class LapStoppedState implements StopwatchState {
    //Alarm state

    public LapStoppedState(final StopwatchSMStateView sm) {
        this.sm = sm;
    }

    private final StopwatchSMStateView sm;

    @Override
    public void onStartStop() {
        sm.actionAlarmStop();
        sm.actionReset(); //changed to reset temp.
        sm.toStoppedState();
    }

    @Override
    public void onLapReset() {
        sm.actionAlarmStop();
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
        return R.string.LAP_STOPPED;
    }
}

