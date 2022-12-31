package com.devtaghreed.puzzels;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.devtaghreed.puzzels.Model.FalseService;
import com.devtaghreed.puzzels.Model.MyService;
import com.devtaghreed.puzzels.Model.TrueService;
import com.devtaghreed.puzzels.databinding.FragmentTrueAndFalseBinding;


public class TrueAndFalseFragment extends Fragment {

    private static final String ARG_questionTvTrueOrFalse = "title";
    private static final String ARG_questionBtnFalse = "questionBtnFalse";
    private static final String ARG_questionBtnTrue = "questionBtnTrue";

    private static final String ARG_Hint = "hint";
    private static final String ARG_true_answer = "true_answer";
    private static final String ARG_Points = "points";
    private String true_answer;
    private String hint;
    private int points;

    private String questionTvTrueOrFalse;
    private String questionBtnFalse;
    private String questionBtnTrue;

    public TrueAndFalseFragment() {
    }

    public static TrueAndFalseFragment newInstance(String questionTvTrueOrFalse, String true_answer, String hint, int point) {
        TrueAndFalseFragment fragment = new TrueAndFalseFragment();
        Bundle args = new Bundle();

        args.putString(ARG_questionTvTrueOrFalse, questionTvTrueOrFalse);
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

            questionTvTrueOrFalse = getArguments().getString(ARG_questionTvTrueOrFalse);
            questionBtnFalse = getArguments().getString(ARG_questionBtnFalse);
            questionBtnTrue = getArguments().getString(ARG_questionBtnTrue);

            true_answer = getArguments().getString(ARG_true_answer);
            hint = getArguments().getString(ARG_Hint);
            points = getArguments().getInt(ARG_Points);

        }
    }
    String answer= "";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentTrueAndFalseBinding binding = FragmentTrueAndFalseBinding.inflate(inflater, container, false);
        binding.questionTvTrueOrFalse.setText(questionTvTrueOrFalse);


        binding.questionImgTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer = "true";
                if (answer.equals(true_answer)){
                    TrueDialogFragment trueDialog = TrueDialogFragment.newInstance(hint);
                    trueDialog.show(getActivity().getSupportFragmentManager(),"true Dialog");

                    Intent intent = new Intent(getActivity(), TrueService.class);
                    getActivity().startService(intent);

                }else {
                    FalseDialogFragment falseDialog = FalseDialogFragment.newInstance(hint);
                    falseDialog.show(getActivity().getSupportFragmentManager(),"false Dialog");

                    Intent intent = new Intent(getActivity(), FalseService.class);
                    getActivity().startService(intent);
                }
            }
        });

        binding.questionImgFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer = "false";
                if (answer.equals(true_answer)){
                    TrueDialogFragment trueDialog = TrueDialogFragment.newInstance(hint);
                    trueDialog.show(getActivity().getSupportFragmentManager(),"true Dialog");

                    Intent intent = new Intent(getActivity(), TrueService.class);
                    getActivity().startService(intent);

                }else {
                    FalseDialogFragment falseDialog = FalseDialogFragment.newInstance(hint);
                    falseDialog.show(getActivity().getSupportFragmentManager(),"false Dialog");

                    Intent t = new Intent(getActivity(), FalseService.class);
                    getActivity().startService(t);
                }
            }
        });
        return binding.getRoot();
    }


}