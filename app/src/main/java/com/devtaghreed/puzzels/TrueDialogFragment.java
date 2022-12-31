package com.devtaghreed.puzzels;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.devtaghreed.puzzels.databinding.FragmentTrueDialogBinding;


public class TrueDialogFragment extends DialogFragment {

    private static final String ARG_HINT = "hint";

    private String Hint;

    public TrueDialogFragment() {
        // Required empty public constructor
    }

    public static TrueDialogFragment newInstance(String hint) {
        TrueDialogFragment fragment = new TrueDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_HINT, hint);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Hint = getArguments().getString(ARG_HINT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentTrueDialogBinding binding = FragmentTrueDialogBinding.inflate(inflater , container , false);

        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.dialog);
        binding.lay.setAnimation(animation);



        binding.tvHintTrue.setText(Hint);

        return binding.getRoot();
    }
}