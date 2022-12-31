package com.devtaghreed.puzzels;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.devtaghreed.puzzels.Model.Adapter;
import com.devtaghreed.puzzels.Model.pagerAdapter;
import com.devtaghreed.puzzels.RoomDataBase.Question;
import com.devtaghreed.puzzels.RoomDataBase.ViewModel;
import com.devtaghreed.puzzels.databinding.ActivityLevelBinding;

import java.util.ArrayList;
import java.util.List;

public class LevelActivity extends AppCompatActivity {

    ActivityLevelBinding binding;
    String title, hint, true_answer, answer_1, answer_2, answer_3, answer_4;
    int points;
    ViewModel viewModel;
    ArrayList<Fragment> fragmentArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLevelBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setTitle("Levels : " + Adapter.levelNumber);

        Intent intent = getIntent();
        int levelId = intent.getIntExtra("levelId", -1);
        Log.d(LevelActivity.class.getSimpleName()+"question",levelId+"");
        viewModel = new ViewModelProvider(LevelActivity.this).get(ViewModel.class);


        viewModel.GetAllQuestionById(levelId).observe(LevelActivity.this, new Observer<List<Question>>() {
            @Override
            public void onChanged(List<Question> questionsList) {

                fragmentArrayList = new ArrayList<>();

                int x = questionsList.size();
                Toast.makeText(LevelActivity.this, String.valueOf(x), Toast.LENGTH_SHORT).show();
                for (int i = 0; i < questionsList.size(); i++) {
                    Question question = questionsList.get(i);
                    Log.d(LevelActivity.class.getSimpleName()+"question",question.getTitle().toString());

                    if (question.getPatternId() == 1) {
                        title = question.getTitle();
                        true_answer = question.getTrue_answer();
                        hint = question.getHint();
                        points = question.getPoints();
                        fragmentArrayList.add(TrueAndFalseFragment.newInstance(title, true_answer, hint, points));

                    } else if (question.getPatternId() == 2) {
                        title = question.getTitle();
                        answer_1 = question.getAnswer_1();
                        answer_2 = question.getAnswer_2();
                        answer_3 = question.getAnswer_3();
                        answer_4 = question.getAnswer_4();
                        true_answer = question.getTrue_answer();
                        hint = question.getHint();
                        points = question.getPoints();
                        fragmentArrayList.add(ChooseFragment.newInstanceChoose(title, answer_1, answer_2, answer_3, answer_4, true_answer, hint, points));

                    } else {
                        title = question.getTitle();
                        true_answer = question.getTrue_answer();
                        hint = question.getHint();
                        points = question.getPoints();
                        fragmentArrayList.add(CompleteFragment.newInstance(title, true_answer, hint, points));
                    }

                }
                pagerAdapter adapter = new pagerAdapter(LevelActivity.this, fragmentArrayList);
                binding.levelVp.setAdapter(adapter);
            }
        });


    }
}