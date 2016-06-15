package com.motivateme;

import android.content.Context;
import android.media.MediaPlayer;

public class SoundMethod {
    public static MediaPlayer player;

    public void soundPlayer(Context ctx, int raw_id) {
        player = MediaPlayer.create(ctx, raw_id);
        player.setLooping(false);
        player.setVolume(100, 100);
    }

    public void start() {
        player.start();
    }

    public MediaPlayer getPlayer() {
        return player;
    }
}
