package com.devtaghreed.puzzels;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.devtaghreed.puzzels.Model.Adapter;
import com.devtaghreed.puzzels.Model.onClickListener;
import com.devtaghreed.puzzels.RoomDataBase.Level;
import com.devtaghreed.puzzels.RoomDataBase.ViewModel;
import com.devtaghreed.puzzels.databinding.ActivityStartPlayingBinding;

import java.util.ArrayList;
import java.util.List;

public class StartPlayingActivity extends AppCompatActivity {

    ActivityStartPlayingBinding binding;
    List<Level> levelArrayList;
    ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStartPlayingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setTitle("Levels");

        levelArrayList = new ArrayList<>();
        viewModel = new ViewModelProvider(this).get(ViewModel.class);

        viewModel.GetAllLevel().observe(this, new Observer<List<Level>>() {
            @Override
            public void onChanged(List<Level> levels) {
                levelArrayList = levels;

                Adapter adapter = new Adapter(levelArrayList, new onClickListener() {
                    @Override
                    public void onClick(int id) {
                        Log.d(StartPlayingActivity.class.getSimpleName()+"question",id+"");

                        Intent intent = new Intent(StartPlayingActivity.this,LevelActivity.class);
                        intent.putExtra("levelId", id);
                        startActivity(intent);
                    }
                });

                binding.rv.setAdapter(adapter);
                binding.rv.setLayoutManager(new GridLayoutManager(StartPlayingActivity.this,2));

            }
        });

    }


}