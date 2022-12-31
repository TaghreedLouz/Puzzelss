package com.devtaghreed.puzzels.Model;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.devtaghreed.puzzels.R;

public class FalseService extends Service {

    MediaPlayer mediaPlayerFalse;

    public FalseService() {
    }



    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayerFalse = MediaPlayer.create(this, R.raw.false_answer);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mediaPlayerFalse.start();
        super.onStartCommand(intent, flags, startId);
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayerFalse.stop();
        mediaPlayerFalse.release();
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}