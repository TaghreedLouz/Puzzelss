package com.devtaghreed.puzzels;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devtaghreed.puzzels.Model.FalseService;
import com.devtaghreed.puzzels.Model.TrueService;
import com.devtaghreed.puzzels.databinding.FragmentChooseBinding;


public class ChooseFragment extends Fragment {

    private static final String ARG_questionTvChoose = "questionTvChoose";
    private static final String ARG_questionRB1 = "questionRB1";
    private static final String ARG_questionRB2 = "questionRB2";
    private static final String ARG_questionRB3 = "questionRB3";
    private static final String ARG_questionRB4 = "questionRB4";

    private String questionTvChoose;
    private String questionRB1;
    private String questionRB2;
    private String questionRB3;
    private String questionRB4;

    private static final String ARG_Hint = "hint";
    private static final String ARG_true_answer = "true_answer";
    private static final String ARG_Points = "points";
    private String true_answer;
    private String hint;
    private int points;

    public static ChooseFragment newInstanceChoose(
            String questionTvChoose, String questionRB1, String questionRB2, String questionRB3, String questionRB4, String true_answer, String hint, int point) {
        ChooseFragment fragment = new ChooseFragment();
        Bundle args = new Bundle();

        args.putString(ARG_questionTvChoose, questionTvChoose);
        args.putString(ARG_questionRB1, questionRB1);
        args.putString(ARG_questionRB2, questionRB2);
        args.putString(ARG_questionRB3, questionRB3);
        args.putString(ARG_questionRB4, questionRB4);

        args.putString(ARG_true_answer, true_answer);
        args.putString(ARG_Hint, hint);
        args.putInt(ARG_Points, point);

        fragment.setArguments(args);
        return fragment;
    }

    public ChooseFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            questionTvChoose = getArguments().getString(ARG_questionTvChoose);
            questionRB1 = getArguments().getString(ARG_questionRB1);
            questionRB2 = getArguments().getString(ARG_questionRB2);
            questionRB3 = getArguments().getString(ARG_questionRB3);
            questionRB4 = getArguments().getString(ARG_questionRB4);

            true_answer = getArguments().getString(ARG_true_answer);
            hint = getArguments().getString(ARG_Hint);
            points = getArguments().getInt(ARG_Points);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentChooseBinding binding = FragmentChooseBinding.inflate(inflater, container, false);

        //Choose
        binding.questionTvChoose.setText(questionTvChoose);
        binding.questionRB1.setText(questionRB1);
        binding.questionRB2.setText(questionRB2);
        binding.questionRB3.setText(questionRB3);
        binding.questionRB4.setText(questionRB4);

        String rb1 = binding.questionRB1.getText().toString();
        String rb2 = binding.questionRB2.getText().toString();
        String rb3 = binding.questionRB3.getText().toString();
        String rb4 = binding.questionRB4.getText().toString();


        binding.questionRB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rb1.equals(true_answer)) {
                    TrueDialogFragment trueDialog = TrueDialogFragment.newInstance(hint);
                    trueDialog.show(getActivity().getSupportFragmentManager(), "true Dialog");

                    Intent intent = new Intent(getActivity(), TrueService.class);
                    getActivity().startService(intent);

                }else {
                    FalseDialogFragment falseDialog = FalseDialogFragment.newInstance(hint);
                    falseDialog.show(getActivity().getSupportFragmentManager(), "false Dialog");

                    Intent intent = new Intent(getActivity(), FalseService.class);
                    getActivity().startService(intent);
                }
            }
        });

        binding.questionRB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rb2.equals(true_answer)) {
                    TrueDialogFragment trueDialog = TrueDialogFragment.newInstance(hint);
                    trueDialog.show(getActivity().getSupportFragmentManager(), "true Dialog");

                }else {
                    FalseDialogFragment falseDialog = FalseDialogFragment.newInstance(hint);
                    falseDialog.show(getActivity().getSupportFragmentManager(), "false Dialog");
                }
            }
        });

        binding.questionRB3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rb3.equals(true_answer)) {
                    TrueDialogFragment trueDialog = TrueDialogFragment.newInstance(hint);
                    trueDialog.show(getActivity().getSupportFragmentManager(), "true Dialog");

                }else {
                    FalseDialogFragment falseDialog = FalseDialogFragment.newInstance(hint);
                    falseDialog.show(getActivity().getSupportFragmentManager(), "false Dialog");
                }
            }
        });


        binding.questionRB4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rb4.equals(true_answer)) {
                    TrueDialogFragment trueDialog = TrueDialogFragment.newInstance(hint);
                    trueDialog.show(getActivity().getSupportFragmentManager(), "true Dialog");

                }else {
                    FalseDialogFragment falseDialog = FalseDialogFragment.newInstance(hint);
                    falseDialog.show(getActivity().getSupportFragmentManager(), "false Dialog");
                }
            }
        });


        return binding.getRoot();
    }
}