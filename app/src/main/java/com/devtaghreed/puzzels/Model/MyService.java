package com.devtaghreed.puzzels.Model;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.devtaghreed.puzzels.R;

public class MyService extends Service {

    MediaPlayer mediaPlayerBG;
    public MyService() {
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayerBG = MediaPlayer.create(this, R.raw.background_sound);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mediaPlayerBG.start();
        super.onStartCommand(intent, flags, startId);
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayerBG.stop();

        //todo why we use release
        mediaPlayerBG.release();
    }


    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}