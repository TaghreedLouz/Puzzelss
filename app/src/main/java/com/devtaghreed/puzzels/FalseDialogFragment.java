package com.devtaghreed.puzzels;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.devtaghreed.puzzels.databinding.FragmentFalseDialogBinding;


public class FalseDialogFragment extends DialogFragment {


    private static final String ARG_HINT = "hint";

    private String HINT;

    public FalseDialogFragment() {
        // Required empty public constructor
    }

    public static FalseDialogFragment newInstance(String hint) {
        FalseDialogFragment fragment = new FalseDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_HINT, hint);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            HINT = getArguments().getString(ARG_HINT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentFalseDialogBinding binding = FragmentFalseDialogBinding.inflate(inflater,container,false);

        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.dialog);
        binding.lay.setAnimation(animation);

        binding.tvHintFalse.setText(HINT);

        return binding.getRoot();
    }
}