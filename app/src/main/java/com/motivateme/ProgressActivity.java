package com.motivateme;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class ProgressActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        ObjectAnimator animation = ObjectAnimator.ofInt(progressBar, "progress", 0, 400); // see this max value coming back here, we animale towards that value
        animation.setDuration(1500); //in milliseconds
        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();

        Intent i = getIntent();
        Goal goal = (Goal) i.getSerializableExtra("goal");
        TextView mLevel = (TextView) findViewById(R.id.tvGoalLevel);
        mLevel.setText("You're level 4");

        Toast.makeText(ProgressActivity.this, "You gained 300 points for losing 20 pounds!", Toast.LENGTH_LONG).show();

    }

}
