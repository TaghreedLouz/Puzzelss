package com.devtaghreed.puzzels;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.devtaghreed.puzzels.Model.parsJson;
import com.devtaghreed.puzzels.RoomDataBase.ViewModel;
import com.devtaghreed.puzzels.databinding.ActivitySplashBinding;


public  class Splash_Activity extends AppCompatActivity {
    ActivitySplashBinding binding;
    Animation animation;
    static ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        com.devtaghreed.puzzels.Model.parsJson p = new parsJson(getApplicationContext());

        //pars
        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        parsJson parsJson = new parsJson(this);
        parsJson.parsJsonFromAssetsForLevel(com.devtaghreed.puzzels.Model.parsJson.readFromAssets(getApplicationContext()));


        //animation & thread
        animation = AnimationUtils.loadAnimation(this, R.anim.splash);
        binding.splashImg.setAnimation(animation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getBaseContext(), HomeActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}