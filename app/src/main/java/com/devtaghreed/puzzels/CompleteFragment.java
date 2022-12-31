package com.devtaghreed.puzzels;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.devtaghreed.puzzels.Model.FalseService;
import com.devtaghreed.puzzels.Model.TrueService;
import com.devtaghreed.puzzels.databinding.FragmentCompleteBinding;

public class CompleteFragment extends Fragment {

    private static final String ARG_questionTvComplete = "questionTvComplete";
    private static final String ARG_questionEtAnswerComplete = "questionEtAnswerComplete";
    private static final String ARG_questionBtnCheck = "questionBtnCheck";

    private static final String ARG_Hint = "hint";
    private static final String ARG_true_answer = "true_answer";
    private static final String ARG_Points = "points";

    private String true_answer;
    private String hint;
    private int points;

    private String questionTvComplete;
    private String questionEtAnswerComplete;
    private String questionBtnCheck;


    public CompleteFragment() {
    }

    public static CompleteFragment newInstance(String questionTvComplete, String true_answer, String hint, int point) {
        CompleteFragment fragment = new CompleteFragment();
        Bundle args = new Bundle();

        args.putString(ARG_questionTvComplete, questionTvComplete);

        args.putString(ARG_true_answer, true_answer);
        args.putString(ARG_Hint, hint);
        args.putInt(ARG_Points, point);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            questionTvComplete = getArguments().getString(ARG_questionTvComplete);
            questionEtAnswerComplete = getArguments().getString(ARG_questionEtAnswerComplete);
            questionBtnCheck = getArguments().getString(ARG_questionBtnCheck);

            true_answer = getArguments().getString(ARG_true_answer);
            hint = getArguments().getString(ARG_Hint);
            points = getArguments().getInt(ARG_Points);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentCompleteBinding binding = FragmentCompleteBinding.inflate(inflater, container, false);

        binding.questionTvComplete.setText(questionTvComplete);

        binding.questionBtnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answer = binding.questionEtAnswerComplete.getText().toString().trim();

                if (answer.isEmpty()) {
                    binding.questionEtAnswerComplete.setError("Enter the answer");
                    binding.questionEtAnswerComplete.requestFocus();
                }else
                {

                    if (answer.equals(true_answer)) {
                        TrueDialogFragment trueDialog = TrueDialogFragment.newInstance(hint);
                        trueDialog.show(getActivity().getSupportFragmentManager(), "true Dialog");
                        binding.questionEtAnswerComplete.setText("");

                        Intent intent = new Intent(getActivity(), TrueService.class);
                        getActivity().startService(intent);

                    } else {
                        FalseDialogFragment falseDialog = FalseDialogFragment.newInstance(hint);
                        falseDialog.show(getActivity().getSupportFragmentManager(), "false Dialog");
                        binding.questionEtAnswerComplete.setText("");

                        Intent intent = new Intent(getActivity(), FalseService.class);
                        getActivity().startService(intent);
                    }

                }
            }
        });

        return binding.getRoot();
    }
}