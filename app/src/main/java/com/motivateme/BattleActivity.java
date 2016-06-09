package com.motivateme;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BattleActivity extends AppCompatActivity implements Chronometer.OnChronometerTickListener {

    private Chronometer mChronometer;
    private Button mConfirm;
    private String target;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Toast.makeText(BattleActivity.this, "Press the floating action button to start the battle", Toast.LENGTH_LONG).show();

        mChronometer = (Chronometer) findViewById(R.id.cmTimer);
        Intent current = getIntent();
        ImageView mPicture = (ImageView) findViewById(R.id.ivBattlePartnerPic);
        TextView mPartnerName = (TextView) findViewById(R.id.tvPartnerName);
        String name = current.getStringExtra("name");
        mPartnerName.setText(name);
        TextView mDifficulty = (TextView) findViewById(R.id.tvDifficulty);
        String difficulty = current.getStringExtra("difficulty");
        mDifficulty.setText(difficulty + " Mode");
        mPicture.setImageResource(current.getIntExtra("picture", 0));

        mConfirm = (Button) findViewById(R.id.btnConfirmTime);
        TextView mTargetTime = (TextView) findViewById(R.id.tvTargetTime);
        double targetTime = current.getDoubleExtra("seconds", 0);
        TextView mPartnerTime = (TextView) findViewById(R.id.tvPartnerTime);
        mPartnerTime.setText(String.format("Orig. %s", targetTime));

        if (name.equals("Usain Bolt")) {
            target = String.valueOf((int) Math.ceil(getSprintTarget(difficulty, targetTime)));
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
                stopTimer();
                Toast.makeText(BattleActivity.this, mChronometer.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        mChronometer.setOnChronometerTickListener(this);
    }

    public void startTimer() {
        resetTimer();
        mChronometer.start();
        mConfirm.setVisibility(View.VISIBLE);
    }

    public void stopTimer() {
        mChronometer.stop();
    }

    public void resetTimer() {
        mChronometer.setBase(SystemClock.elapsedRealtime());
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

    @Override
    public void onChronometerTick(Chronometer chronometer) {
        if (chronometer.getText().equals("00:" + target)) {
            stopTimer();
            mChronometer.setTextColor(getResources().getColor(R.color.colorAccent));
        }
    }
}
