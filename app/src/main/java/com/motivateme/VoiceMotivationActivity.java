package com.motivateme;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class VoiceMotivationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_motivation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonMethod.SoundPlayer(getBaseContext(), R.raw.destroy_your_goals);
            }
        });

    }

    @Override
    protected void onPause() {
        if (CommonMethod.player != null) CommonMethod.player.stop();
        super.onPause();
    }
}

class CommonMethod {
    public static MediaPlayer player;

    public static void SoundPlayer(Context ctx, int raw_id) {
        player = MediaPlayer.create(ctx, raw_id);
        player.setLooping(false);
        player.setVolume(100, 100);

        //player.release();
        player.start();
    }
}