package com.devtaghreed.puzzels.Model;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.devtaghreed.puzzels.R;

public class TrueService extends Service {
    public TrueService() {
    }

    MediaPlayer mediaPlayerTrue;

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayerTrue = MediaPlayer.create(this, R.raw.right_answer);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mediaPlayerTrue.start();
        super.onStartCommand(intent, flags, startId);
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayerTrue.stop();
        mediaPlayerTrue.release();
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}