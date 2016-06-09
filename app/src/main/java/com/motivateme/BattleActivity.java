package com.motivateme;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BattleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        Toast.makeText(BattleActivity.this, "Press the floating action button to start the battle", Toast.LENGTH_LONG).show();

        Intent current = getIntent();
        ImageView mPicture = (ImageView) findViewById(R.id.ivBattlePartnerPic);
        TextView mPartnerName = (TextView) findViewById(R.id.tvPartnerName);
        mPartnerName.setText(current.getStringExtra("name"));
        TextView mDifficulty = (TextView) findViewById(R.id.tvDifficulty);
        mDifficulty.setText(current.getStringExtra("difficulty") + " Mode");
        mPicture.setImageResource(current.getIntExtra("picture", 0));

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        final boolean[] iconSwitch = {false};
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fab.setImageResource(iconSwitch[0]
                        ? R.drawable.ic_play_circle_outline_black_24dp
                        : R.drawable.ic_stop_black_24dp);
                iconSwitch[0] = !iconSwitch[0];
            }
        });
    }
}
