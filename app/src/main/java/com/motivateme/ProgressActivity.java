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

        final Intent current = getIntent();

        new AlertDialog.Builder(this)
                .setTitle("Battle result")
                .setMessage(current.getBooleanExtra("result", false)
                        ? "Well done! You've won the battle!\nPlay Again?"
                        : "Too bad! You lost.\nPlay Again?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String time = current.getStringExtra("time").replace(":", ".");
                        startActivity(new Intent(ProgressActivity.this, BattleResultActivity.class)
                                .putExtra("time", time.replace("00.", "") + ".46"));
                    }
                })
                .show();
    }

}
