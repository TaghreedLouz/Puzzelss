package com.devtaghreed.puzzels;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.devtaghreed.puzzels.Model.appUtility;
import com.devtaghreed.puzzels.databinding.ActivityStartPlayingBinding;

public class StartPlayingActivity extends AppCompatActivity {

    ActivityStartPlayingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStartPlayingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        String jsonString = appUtility.readFromAssets(getApplicationContext(),"puzzleGameData.json");
        //parsJsonFromAssets(jsonString);
    }


}