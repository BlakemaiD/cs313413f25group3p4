package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;

class SettingStateTimer implements StopwatchState {
    //In context of the Timer, since LapRunningState is only used in stopwatch,
    //we will repurpose it to be the setting of the timer

    public SettingStateTimer(final StopwatchSMStateView sm) {
        this.sm = sm;
    }

    private final StopwatchSMStateView sm;

    private int countSeconds = 0;


    @Override
    public void onStartStop() {

        sm.actionInc();
        countSeconds = 0;

        if(sm.getTime() == 99) {
            sm.actionBeep();
            sm.toRunningState();
            sm.actionStart();
        } else if (sm.getTime() > 99) {
            sm.actionBeep();
            sm.actionReset();
            sm.toStoppedState();
        } else {
            sm.toLapRunningState();
        }
    }

    @Override
    public void onLapReset() {
        sm.actionReset();
        sm.toStoppedState();
        countSeconds = 0;
    }

    @Override
    public void onTick() {
        countSeconds++;

        if(countSeconds >= 3 && sm.getTime() > 0) {
            sm.actionBeep();
            sm.actionDec();
            sm.toRunningState();
        }
    }

    @Override
    public void updateView() {
        sm.updateUIRuntime();
    }

    @Override
    public int getId() {
        return R.string.LAP_RUNNING;
    }
}
