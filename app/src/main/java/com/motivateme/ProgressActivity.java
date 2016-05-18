package com.motivateme;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ProgressActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent i = getIntent();
        Goal goal = (Goal) i.getSerializableExtra("goal");

        TextView mProgressText = (TextView) findViewById(R.id.progressText);
        mProgressText.setText(String.format("%s/%s", goal.getCurrent(), goal.getTarget()));

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setMax(goal.getTarget());
        ObjectAnimator animation = ObjectAnimator.ofInt(progressBar, "progress", 0, goal.getCurrent());
        animation.setDuration(1500); //in milliseconds
        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();

        TextView mLevel = (TextView) findViewById(R.id.tvGoalLevel);

    }

}
