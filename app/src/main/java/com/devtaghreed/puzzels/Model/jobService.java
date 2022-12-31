package com.devtaghreed.puzzels.Model;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.devtaghreed.puzzels.R;

public class jobService extends JobService {

    public static final String CHANNEL_ID = "ChannelId";
    boolean stopped = false;

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        notification(jobParameters);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        stopped = true;
        return false;
    }

    public void notification(JobParameters jobParameters) {

        if (stopped){
            jobFinished(jobParameters,false);
            return;
        }

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "channel name", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_logo);
        builder.setContentTitle("Puzzles Game");
        builder.setContentText("it’s been a long time since last played");
        builder.setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getApplicationContext());
        managerCompat.notify(1, builder.build());

    }
}
