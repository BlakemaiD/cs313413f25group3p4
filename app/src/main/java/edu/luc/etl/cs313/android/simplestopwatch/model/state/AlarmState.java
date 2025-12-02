package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;

class AlarmState implements StopwatchState {

    public AlarmState(final StopwatchSMStateView sm) {
        this.sm = sm;
    }

    private final StopwatchSMStateView sm;


    // Listener for Alarm action?


    @Override
    public void onStartStop() {
        sm.actionStop();
        sm.toStoppedState();

    }

}