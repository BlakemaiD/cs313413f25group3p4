package edu.luc.etl.cs313.android.simplestopwatch.model.time;

import static edu.luc.etl.cs313.android.simplestopwatch.common.Constants.*;

/**
 * An implementation of the stopwatch data model.
 * TODO: Implementate the timer model!
 */
public class DefaultTimeModel implements TimeModel {

    private final int MAX_TIME = 99; //maximum number on timer

    private int runningTime = 0;

    private int lapTime = -1;

    @Override
    public void resetRuntime() {
        runningTime = 0;
    }

    @Override
    public void incRuntime() { //TODO:ADD Timer modifications //Done
       if (runningTime < MAX_TIME) {
            runningTime += 1;
       }
    }

    //Add a decRuntime() Method for when 3 seconds pass to decrease time/
    public void decRuntime() {
        if (runningTime > 0) {
            runningTime -= 1;
        }
    }

    @Override
    public int getRuntime() {
        return runningTime;
    }

    @Override
    public void setLaptime() { //Won't need this for timer
        lapTime = runningTime;
    }

    @Override
    public int getLaptime() {
        return lapTime;
    }
}