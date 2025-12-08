package edu.luc.etl.cs313.android.simplestopwatch.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

//Some of the android media sounds, used from clickcounter android
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;

import java.util.Locale;

import edu.luc.etl.cs313.android.simplestopwatch.R;
import edu.luc.etl.cs313.android.simplestopwatch.common.StopwatchModelListener;
import edu.luc.etl.cs313.android.simplestopwatch.model.ConcreteStopwatchModelFacade;
import edu.luc.etl.cs313.android.simplestopwatch.model.StopwatchModelFacade;

/**
 * A thin adapter component for the stopwatch.
 *
 * @author laufer
 */
public class StopwatchAdapter extends Activity implements StopwatchModelListener {

    private static String TAG = "stopwatch-android-activity";

    /**
     * The state-based dynamic model.
     */
    private StopwatchModelFacade model;

    protected void setModel(final StopwatchModelFacade model) {
        this.model = model;
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // inject dependency on view so this adapter receives UI events
        setContentView(R.layout.activity_new_main);


        //test sound, having problems hearing
        //playDefaultNotification();

        // inject dependency on model into this so model receives UI events
        this.setModel(new ConcreteStopwatchModelFacade());
        // inject dependency on this into model to register for UI updates
        model.setModelListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        model.start();
    }

    /**
     *
     * @param time
     */
   public void onTimeUpdate(final int time) { //Display will show the
        // UI adapter responsibility to schedule incoming events on UI thread
        runOnUiThread(() -> {
            final TextView tvS = findViewById(R.id.seconds);
            final TextView tvM = findViewById(R.id.minutes);
            /*
            tvS.setText(String.format(locale,"%02d", time % Constants.SEC_PER_MIN));
            tvM.setText(String.format(locale,"%02d", time / Constants.SEC_PER_MIN));
             */
            final var locale = Locale.getDefault();
            tvS.setText(String.format(locale, "%02d", time));
            tvM.setText("");
        });
    }

    /**
     * Updates the state name in the UI.
     * @param stateId
     */
    public void onStateUpdate(final int stateId) {
        // UI adapter responsibility to schedule incoming events on UI thread
        runOnUiThread(() -> {
            final TextView stateName = findViewById(R.id.stateName);
            stateName.setText(getString(stateId));
        });
    }

    //Alarm sound
    private Ringtone alarmTone;

    /** Plays the default notification */
    /** This is from clickcounter*/
    protected void playDefaultNotification() {
        final Uri defaultRingtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        final MediaPlayer mediaPlayer = new MediaPlayer();
        final var context = getApplicationContext();

        try {
            mediaPlayer.setDataSource(context, defaultRingtoneUri);
            mediaPlayer.setAudioAttributes(
                    new AudioAttributes.Builder()
                            .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                            .build());
            mediaPlayer.prepare();
            mediaPlayer.setOnCompletionListener(MediaPlayer::release);
            mediaPlayer.start();
        } catch (final java.io.IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /** Starts a looping alarm */
    protected void startAlarmSound() {
        if (alarmTone == null) {
            Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
            alarmTone = RingtoneManager.getRingtone(getApplicationContext(), alarmUri);
        }
        if (alarmTone != null && !alarmTone.isPlaying()) {
            alarmTone.play();
        }
    }

    /** Stops the looping alarm sound, if any. */
    public void stopAlarmSound() {
        if (alarmTone != null && alarmTone.isPlaying()) {
            alarmTone.stop();
        }
    }

    @Override
    public void onBeep() {
        // Make sure this runs on the UI thread
        runOnUiThread(this::playDefaultNotification);
    }
    @Override
    public void onAlarmStart() {
        /** For sake of time, I was only able to add the beep audio, but not the alarm
         * I tried different ways, but was unable to get the alarm to stop...*/
        //runOnUiThread(this::startAlarmSound);
    }
    @Override
    public void onAlarmStop() {
        //runOnUiThread(this::stopAlarmSound);
    }
    public void onStartStop(final View view) {
        model.onStartStop();
    }

    public void onLapReset(final View view)  { //Could be removed.
        model.onLapReset();
    }
}
