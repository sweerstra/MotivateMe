package com.motivateme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

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

        final ImageView mBolt = (ImageView) findViewById(R.id.ivBolt);
        mBolt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChallengeYourselfActivity.this, BattleAthleteActivity.class);
                intent.putExtra("seconds", 9.58);
                intent.putExtra("distance", 100);
                intent.putExtra("name", "Usain Bolt");
                intent.putExtra("picture", R.drawable.usain_bolt);
                startActivity(intent);
            }
        });

        final ImageView mKimetto = (ImageView) findViewById(R.id.ivKimetto);
        mKimetto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChallengeYourselfActivity.this, BattleAthleteActivity.class);
                intent.putExtra("seconds", 1500);
                intent.putExtra("distance", 10000);
                intent.putExtra("name", "Dennis Kimetto");
                intent.putExtra("picture", R.drawable.kimetto);
                startActivity(intent);
            }
        });
    }
}
