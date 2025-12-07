package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;

class RunningState implements StopwatchState {
    //this will be the state for when the time is counting down
    /*
    While running:
        Every tick (1 second):
        Time --.
    If time reaches 0:
        Stop the clock
        Alarm state --> Implement still
    If the button is pressed while running:
        Stop the clock
        time == 0.
     */
    public RunningState(final StopwatchSMStateView sm) {
        this.sm = sm;
    }

    private final StopwatchSMStateView sm;

    @Override
    public void onStartStop() { //if pressed while running -> acts as a cancel button
        sm.actionStop();
        sm.actionReset();
        sm.toStoppedState();
    }

    @Override
    public void onLapReset() { //irrelevent
        sm.actionStop();
        sm.actionReset();
        sm.toStoppedState();
    }

    @Override
    public void onTick() { //if time get to 0 by itself, then timer stops and alarm starts beeping
        //From settingstate, when seconds after last button is 3 and time > 0, this method will run.
        sm.actionDec();
            if (sm.getTime() == 0) {
                sm.actionStop();
                sm.toLapRunningState();
                sm.actionReset();
            }
        //sm.toRunningState();
    }

    @Override
    public void updateView() {
        sm.updateUIRuntime();
    }

    @Override
    public int getId() {
        return R.string.RUNNING;
    }
}


