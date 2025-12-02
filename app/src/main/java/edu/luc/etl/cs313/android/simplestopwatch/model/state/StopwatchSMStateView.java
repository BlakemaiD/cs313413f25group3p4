package edu.luc.etl.cs313.android.simplestopwatch.model.state;

/**
 * The restricted view states have of their surrounding state machine.
 * This is a client-specific interface in Peter Coad's terminology.
 *
 * @author laufer
 */
interface StopwatchSMStateView {

    // transitions
    void toRunningState();
    void toStoppedState();
    //void toAlarmState();

    // actions
    void actionInit();
    void actionReset();
    void actionStart();
    void actionStop();
    // Idle Increment Action
    //void resetIdleTick();
    //void incIdleTick();
    void actionInc();
    // Timer Countdown Action
    void actionDec();
    void actionUpdateView();
    // Alarm Action
    //void actionAlarm();

    // state-dependent UI updates
    void updateUIRuntime();
    
}
