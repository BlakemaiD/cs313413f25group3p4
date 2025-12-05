package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;

class SettingStateTimer implements StopwatchState {
    //In context of the Timer, since LapRunningState is only used in stopwatch,
    //we will repurpose it to be the setting of the timer

    public SettingStateTimer(final StopwatchSMStateView sm) {
        this.sm = sm;
    }

    private final StopwatchSMStateView sm;

    @Override
    public void onStartStop() { //TODO: Implement a loop that checks the time
        //when it is pressed

        if(sm.getTime() < 99) {
            sm.actionInc();
            sm.toLapRunningState();
        } else {
            //if time is >= 99, (Has not implemented the 3sec); it beeps and starts running.
            //sm.beep(); //Add the beep sound once it works
            sm.toRunningState();
            sm.actionStart();
        }



        sm.actionStop();
        sm.toLapStoppedState();
    }

    @Override
    public void onLapReset() {
       // sm.toRunningState();
       // sm.actionUpdateView();
    }

    @Override
    public void onTick() {
       // sm.actionInc();
       // sm.toLapRunningState();
    }

    @Override
    public void updateView() {
        sm.updateUILaptime();
    }

    @Override
    public int getId() {
        return R.string.LAP_RUNNING;
    }
}
