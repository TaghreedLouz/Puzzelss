package com.devtaghreed.puzzels;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.devtaghreed.puzzels.Model.MyService;
import com.devtaghreed.puzzels.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;
    Intent intent;
    SharedPreferences sp;
    SharedPreferences.Editor edit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setTitle("Home");

        //background sound
        intent = new Intent(getApplicationContext(), MyService.class);
        startService(intent);


//        sp = getSharedPreferences("SharedPreferences", MODE_PRIVATE);
//        edit = sp.edit();
//        sp.getBoolean("sw",false);

        Animation animationStart = AnimationUtils.loadAnimation(this, R.anim.btn1);
        binding.homeBtnStart.setAnimation(animationStart);

        Animation animationSettings = AnimationUtils.loadAnimation(this, R.anim.btn2);
        binding.homeBtnSettings.setAnimation(animationSettings);

        Animation animationLogout = AnimationUtils.loadAnimation(this, R.anim.btn1);
        binding.homeBtnLogout.setAnimation(animationLogout);

        //start
        binding.homeBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, StartPlayingActivity.class));
            }
        });

        //settings
        binding.homeBtnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, SettingsActivity.class));
            }
        });

        //logout.
        binding.homeBtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), MyService.class);
                stopService(i);
                finish();

            }
        });

    }
}