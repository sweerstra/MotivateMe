package com.motivateme;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BattleActivity extends AppCompatActivity implements SensorEventListener {

    boolean activityRunning;
    private Chronometer mChronometer;
    private Button mConfirm;
    private String target;
    private int animationTime;
    private SensorManager sensorManager;
    private TextView mSteps;
    private ImageView mPicture;
    private int steps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSteps = (TextView) findViewById(R.id.tvSteps);

        mChronometer = (Chronometer) findViewById(R.id.cmTimer);
        Intent current = getIntent();
        mPicture = (ImageView) findViewById(R.id.ivBattlePartnerPic);

        TextView mPartnerName = (TextView) findViewById(R.id.tvPartnerName);
        String name = current.getStringExtra("name");
        mPartnerName.setText(name);
        TextView mDifficulty = (TextView) findViewById(R.id.tvDifficulty);
        String difficulty = current.getStringExtra("difficulty");
        mDifficulty.setText(difficulty);
        mPicture.setImageResource(current.getIntExtra("picture", 0));

        mConfirm = (Button) findViewById(R.id.btnConfirmTime);
        TextView mTargetTime = (TextView) findViewById(R.id.tvTargetTime);
        double targetTime = current.getDoubleExtra("seconds", 0);
        TextView mPartnerTime = (TextView) findViewById(R.id.tvPartnerTime);
        mPartnerTime.setText(String.valueOf(targetTime));

        if (name.equals("Usain Bolt")) {
            animationTime = (int) Math.ceil(getSprintTarget(difficulty, targetTime));
            target = String.valueOf(animationTime);
            mTargetTime.setText(String.valueOf(Math.round(getSprintTarget(difficulty, targetTime) * 100.0) / 100.0).replace(".", ":"));
        } else {
            mTargetTime.setText(getMarathonTarget(difficulty));
        }

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        final boolean[] iconSwitch = {false};
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fab.setImageResource(iconSwitch[0]
                        ? R.drawable.ic_play_circle_outline_black_24dp
                        : R.drawable.ic_stop_black_24dp);
                if (iconSwitch[0]) {
                    stopTimer();
                } else {
                    startTimer();
                }
                iconSwitch[0] = !iconSwitch[0];
            }
        });

        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endBattle(true);
            }
        });

        mChronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                endTimeReached(chronometer);
            }
        });
    }

    public void endBattle(boolean result) {
        stopTimer();
        startActivity(new Intent(BattleActivity.this, ProgressActivity.class)
                .putExtra("time", mChronometer.getText())
                .putExtra("result", result));
    }

    @Override
    protected void onResume() {
        super.onResume();
        activityRunning = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (countSensor != null) {
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        } else {
            Toast.makeText(this, "Count sensor not available!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        activityRunning = false;
        if (SoundMethod.player != null) SoundMethod.player.stop();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (activityRunning) {
            steps++;
            if (steps == 100) {
                endBattle(true);
            }
            mSteps.setText(String.valueOf(steps));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void startTimer() {
        SoundMethod method = new SoundMethod();
        method.soundPlayer(getBaseContext(), R.raw.start_sequence_cut);
        method.start();
        method.getPlayer().setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                resetTimer();
                mChronometer.start();
                animate(animationTime);
                mConfirm.setVisibility(View.VISIBLE);
            }
        });
    }

    public void stopTimer() {
        mChronometer.stop();
    }

    public void resetTimer() {
        mChronometer.setBase(SystemClock.elapsedRealtime());
        mChronometer.setTextColor(getResources().getColor(R.color.white));
    }

    public String getMarathonTarget(String difficulty) {
        switch (difficulty) {
            case "Easy":
                return "4:00:00";
            case "Medium":
                return "3:00:00";
            case "Hard":
                return "2:35:00";
            default:
                return "00:00";
        }
    }

    public double getSprintTarget(String difficulty, double targetTime) {
        switch (difficulty) {
            case "Easy":
                return targetTime * 3;
            case "Medium":
                return targetTime * 2;
            case "Hard":
                return targetTime * 1.3;
            default:
                return 0;
        }
    }

    public void animate(int targetTime) {
        TranslateAnimation animation = new TranslateAnimation(0.0f, 800.0f, 0.0f, 0.0f);
        animation.setDuration(targetTime * 1000);
        mPicture.startAnimation(animation);
    }

    public void endTimeReached(Chronometer chronometer) {
        String targetText = "00:" + target;
        if (chronometer.getText().equals(targetText)) {
            endBattle(false);
            chronometer.setTextColor(getResources().getColor(R.color.colorAccent));
        }

        /*int halfway = Integer.parseInt(target) / 2;
        if(chronometer.getText().equals("00" + String.valueOf(halfway))){
            //TODO halfway there
        }*/
    }
}
