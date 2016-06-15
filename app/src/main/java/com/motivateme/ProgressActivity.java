package com.motivateme;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class ProgressActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent current = getIntent();

        new AlertDialog.Builder(this)
                .setTitle("Battle result")
                .setMessage(current.getBooleanExtra("result", false)
                        ? "Well done! You've won the battle!\nPlay Again?"
                        : "Too bad! You lost.\nPlay Again?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(ProgressActivity.this, MainActivity.class));
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();


        /*TextView mProgressText = (TextView) findViewById(R.id.progressText);
        mProgressText.setText(String.format("%s/%s", goal.getCurrent(), goal.getTarget()));

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setMax(goal.getTarget());
        ObjectAnimator animation = ObjectAnimator.ofInt(progressBar, "progress", 0, goal.getCurrent());
        animation.setDuration(1500); //in milliseconds
        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();

        TextView mLevel = (TextView) findViewById(R.id.tvGoalLevel);*/

    }

}
