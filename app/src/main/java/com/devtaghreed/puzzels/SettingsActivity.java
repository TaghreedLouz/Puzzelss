package com.devtaghreed.puzzels;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.devtaghreed.puzzels.Model.FalseService;
import com.devtaghreed.puzzels.Model.MyService;
import com.devtaghreed.puzzels.Model.TrueService;
import com.devtaghreed.puzzels.Model.jobService;
import com.devtaghreed.puzzels.databinding.ActivitySettingsBinding;

public class SettingsActivity extends AppCompatActivity {

    ActivitySettingsBinding binding;
    Intent intentBG;
    Intent intentTrue;
    Intent intentFalse;
    SharedPreferences sp;
    SharedPreferences.Editor edit;
    boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setTitle("Settings");
        intentBG = new Intent(getApplicationContext(), MyService.class);
        intentTrue = new Intent(getApplicationContext(), TrueService.class);
        intentFalse = new Intent(getApplicationContext(), FalseService.class);

        sp = getSharedPreferences("SharedPreferences", MODE_PRIVATE);
        edit = sp.edit();

        JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);


        //Notification
        binding.settingsSwNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (binding.settingsSwNotification.isChecked()){
                    JobInfo jobInfo = null;
                    ComponentName componentName = new ComponentName(getBaseContext(), jobService.class);
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        jobInfo = new JobInfo.Builder(111, componentName)
                                .setPeriodic((24*60*60*1000), jobInfo.getMinFlexMillis())
                                //.setMinimumLatency(5000)
                                .setPersisted(true)
                                .build();
                    }
                    jobScheduler.schedule(jobInfo);

                }else {
                    jobScheduler.cancel(111);
                }
            }
        });

        //Sounds
        binding.settingsSwSounds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (binding.settingsSwSounds.isChecked() && isPlaying){
                    startService(intentBG);
                    startService(intentTrue);
                    startService(intentFalse);
                    isPlaying = false;
                    edit.apply();
                }else{
                    stopService(intentBG);
                    stopService(intentTrue);
                    stopService(intentFalse );
                    binding.settingsSwSounds.setChecked(false);
                    isPlaying = true;
                }

                edit.putBoolean("sw",binding.settingsSwSounds.isChecked());
                edit.apply();
            }
        });







//        binding.settingsSwSounds.setOnClickListener(new CompoundButton.OnCheckedChangeListener(){
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
//                if (checked){
//                    startService(intent);
//                }
//                else{
//                    stopService(intent);
//                }
//
//
//                edit.putBoolean("sw",binding.settingsSwSounds.isChecked());
//
//            }
//        });


        binding.settingBtnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(SettingsActivity.this,ProfileActivity.class));
            }
        });
    }
}