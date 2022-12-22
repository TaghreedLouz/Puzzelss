package com.devtaghreed.puzzels;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.devtaghreed.puzzels.databinding.ActivityProfileBinding;

import java.util.Calendar;


public class ProfileActivity extends AppCompatActivity {

    ActivityProfileBinding binding;

    public int Age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //date picker
        binding.profileEtBirthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar now = Calendar.getInstance();

                com.wdullaer.materialdatetimepicker.date.DatePickerDialog dpd = com.wdullaer.materialdatetimepicker.date.DatePickerDialog.newInstance(new com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(com.wdullaer.materialdatetimepicker.date.DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        binding.profileEtBirthdate.setText(String.valueOf(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year));
                        Age = now.get(Calendar.YEAR) - year;
                    }
                }, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
                dpd.show(getSupportFragmentManager(), " null ");
            }
        });
    }
}