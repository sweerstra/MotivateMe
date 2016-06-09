package com.motivateme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

public class BattleAthleteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_athlete);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        Intent current = getIntent();
        final double time = current.getDoubleExtra("time", 0);
        final int distance = current.getIntExtra("distance", 0);
        final int picture = current.getIntExtra("picture", 0);
        final String name = current.getStringExtra("name");

        findViewById(R.id.btnStartBattle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BattleAthleteActivity.this, BattleActivity.class);
                RadioGroup rgDifficulty = (RadioGroup) findViewById(R.id.rgDifficulty);
                RadioButton mDifficulty = (RadioButton) findViewById(rgDifficulty.getCheckedRadioButtonId());
                Switch mQuotes = (Switch) findViewById(R.id.switchAudioQuotes);
                Switch mMidRaceResults = (Switch) findViewById(R.id.switchMidRaceResults);
                intent.putExtra("picture", picture);
                intent.putExtra("name", name);
                intent.putExtra("difficulty", mDifficulty.getText());
                intent.putExtra("quotes", mQuotes.isChecked());
                intent.putExtra("midraceresults", mMidRaceResults.isChecked());
                intent.putExtra("time", time);
                intent.putExtra("distance", distance);
                startActivity(intent);
            }
        });


    }

}
