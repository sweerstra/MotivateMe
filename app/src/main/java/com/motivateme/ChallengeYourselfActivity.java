package com.motivateme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class ChallengeYourselfActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_yourself);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        findViewById(R.id.ivBolt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChallengeYourselfActivity.this, BattleAthleteActivity.class);
                intent.putExtra("seconds", 9.58);
                intent.putExtra("distance", 100);
                startActivity(intent);
            }
        });

        findViewById(R.id.ivKimetto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChallengeYourselfActivity.this, BattleAthleteActivity.class);
                intent.putExtra("seconds", 1500);
                intent.putExtra("distance", 10000);
                startActivity(intent);
            }
        });
    }
}
