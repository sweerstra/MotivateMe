package com.motivateme;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by Sjoerd on 15-6-2016.
 */
public class SoundMethod {
    public static MediaPlayer player;

    public static void SoundPlayer(Context ctx, int raw_id) {
        player = MediaPlayer.create(ctx, raw_id);
        player.setLooping(false);
        player.setVolume(100, 100);

        //player.release();
        player.start();
    }
}
