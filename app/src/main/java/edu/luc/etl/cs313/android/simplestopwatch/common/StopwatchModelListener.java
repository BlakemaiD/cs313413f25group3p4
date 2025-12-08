package edu.luc.etl.cs313.android.simplestopwatch.common;

/**
 * A listener for UI update events.
 * This interface is typically implemented by the adapter, with the
 * events coming from the model.
 *
 * @author laufer
 */
public interface StopwatchModelListener {
    void onTimeUpdate(int timeValue);
    void onStateUpdate(int stateId);

    void onBeep(); //the sound
    void onAlarmStart(); //start beeping ---->See final commit on StopwatchAdapter for details on the usage
    void onAlarmStop(); //stop beeping
}
