package com.devtaghreed.puzzels;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.devtaghreed.puzzels.RoomDataBase.User;
import com.devtaghreed.puzzels.RoomDataBase.ViewModel;
import com.devtaghreed.puzzels.databinding.ActivityProfileBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class ProfileActivity extends AppCompatActivity {

    ActivityProfileBinding binding;
    ViewModel viewModel;
    public final String Gender_key = "Gender";
    SharedPreferences sp;
    SharedPreferences.Editor edit;
    List<User> userArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //initialization
        setTitle("Profile");
        editUnAble();
        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        sp = getSharedPreferences("SharedPreferences", MODE_PRIVATE);
        edit = sp.edit();


        String name_c = binding.profileEtName.getText().toString().trim();
        String email_c = binding.profileEtEmail.getText().toString().trim();
        String birthdate_c = binding.profileEtBirthdate.getText().toString().trim();

        User user_c = new User(name_c, email_c, birthdate_c);
        Log.d("onCreate", "onClick: UserId: " + user_c.getUserId() + "BirthDate: " + user_c.getBirthDate() + "Country: " + user_c.getCountry() + "Name: " + user_c.getName() + "Email: " + user_c.getEmail());

        viewModel.GetAllUser().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                ProfileActivity.this.userArrayList = users;
                User user = new User();
                binding.profileEtName.setText(user.getName());
                binding.profileEtEmail.setText(user.getEmail());
                binding.profileEtBirthdate.setText(user.getBirthDate());
            }
        });

//        if (name_c.isEmpty() || email_c.isEmpty() || birthdate_c.isEmpty()) {
//            //actions
//            editUnAble();
//            binding.profileBtnEdit.setVisibility(View.GONE);
//            binding.profileBtnSave.setVisibility(View.GONE);
//            binding.profileBtnAdd.setVisibility(View.VISIBLE);
//        } else {
//            //actions
//            editUnAble();
//            binding.profileBtnEdit.setVisibility(View.VISIBLE);
//            binding.profileBtnSave.setVisibility(View.GONE);
//            binding.profileBtnAdd.setVisibility(View.GONE);
//        }


        //add
        String nameAdd = binding.profileEtName.getText().toString().trim();
        String emailAdd = binding.profileEtEmail.getText().toString().trim();
        String birthdateAdd = binding.profileEtBirthdate.getText().toString().trim();

        if (nameAdd.isEmpty() || emailAdd.isEmpty() || birthdateAdd.isEmpty()) {
            binding.profileBtnAdd.setVisibility(View.VISIBLE);
            binding.profileBtnSave.setVisibility(View.GONE);
            binding.profileBtnEdit.setVisibility(View.GONE);
        } else {
            binding.profileBtnEdit.setVisibility(View.VISIBLE);
            binding.profileBtnAdd.setVisibility(View.GONE);
            binding.profileBtnSave.setVisibility(View.GONE);
        }

//        viewModel.GetAllUser().observe(ProfileActivity.this, new Observer<List<User>>() {
//            @Override
//            public void onChanged(List<User> userList) {
//                userArrayList = userList;
//                User user = new User();
//                binding.profileEtName.setText(user.getName());
//                binding.profileEtEmail.setText(user.getEmail());
//                binding.profileEtBirthdate.setText(user.getBirthDate());
//            }
//        });

        //add
        binding.profileBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editAble();
                binding.profileBtnAdd.setText("Save");

                String nameAdd = binding.profileEtName.getText().toString().trim();
                String emailAdd = binding.profileEtEmail.getText().toString().trim();
                String birthdateAdd = binding.profileEtBirthdate.getText().toString().trim();

                if (nameAdd.isEmpty()) {
                    binding.profileEtName.setError("Please Enter your Name");
                    binding.profileEtName.requestFocus();
                } else if (emailAdd.isEmpty()) {
                    binding.profileEtEmail.setError("Please Enter your Email Address");
                    binding.profileEtEmail.requestFocus();
                    return;
                } else if (!(emailAdd.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))) {
                    binding.profileEtEmail.setError("Invalid Email Address");
                    binding.profileEtEmail.requestFocus();
                    return;
                } else if (birthdateAdd.isEmpty()) {
                    binding.profileEtBirthdate.setError("Select your Birthdate");
                    binding.profileEtBirthdate.requestFocus();
                    return;
                } else {

                    User userAdd = new User(nameAdd, emailAdd, birthdateAdd);
                    viewModel.InsertUser(userAdd);
                    saveSharedPreferences();
                    Toast.makeText(ProfileActivity.this, "Add Successful", Toast.LENGTH_SHORT).show();
                    Log.d("add", "onClick: UserId: " + userAdd.getUserId() + "BirthDate: " + userAdd.getBirthDate() + "Country: " + userAdd.getCountry() + "Name: " + userAdd.getName() + "Email: " + userAdd.getEmail());

                    //actions
                    editUnAble();
                    binding.profileBtnEdit.setVisibility(View.VISIBLE);
                    binding.profileBtnSave.setVisibility(View.GONE);
                    binding.profileBtnAdd.setVisibility(View.GONE);

                    userArrayList = new ArrayList<>();


                }
            }
        });

        //edit
        binding.profileBtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editAble();
                binding.profileBtnSave.setVisibility(View.VISIBLE);
                binding.profileBtnEdit.setVisibility(View.GONE);
                binding.profileBtnAdd.setVisibility(View.GONE);

            }
        });

        //save
        binding.profileBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nameEdit = binding.profileEtName.getText().toString().trim();
                String emailEdit = binding.profileEtEmail.getText().toString().trim();
                String birthdateEdit = binding.profileEtBirthdate.getText().toString().trim();

                if (nameEdit.isEmpty()) {
                    binding.profileEtName.setError("Please Enter your Name");
                    binding.profileEtName.requestFocus();
                } else if (emailEdit.isEmpty()) {
                    binding.profileEtEmail.setError("Please Enter your Email Address");
                    binding.profileEtEmail.requestFocus();
                    return;
                } else if (!(emailEdit.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))) {
                    binding.profileEtEmail.setError("Invalid Email Address");
                    binding.profileEtEmail.requestFocus();
                    return;
                } else if (birthdateEdit.isEmpty()) {
                    binding.profileEtBirthdate.setError("Select your Birthdate");
                    binding.profileEtBirthdate.requestFocus();
                    return;
                } else {

                    User userEdit = new User(nameEdit, emailEdit, birthdateEdit);
                    viewModel.UpdateUser(userEdit);
                    saveSharedPreferences();
                    Toast.makeText(ProfileActivity.this, "Edit Successful", Toast.LENGTH_SHORT).show();
                    Log.d("edit", "onClick: UserId: " + userEdit.getUserId() + "BirthDate: " + userEdit.getBirthDate() + "Country: " + userEdit.getCountry() + "Name: " + userEdit.getName() + "Email: " + userEdit.getEmail());

                    //actions
                    editUnAble();
                    binding.profileBtnEdit.setVisibility(View.VISIBLE);
                    binding.profileBtnSave.setVisibility(View.GONE);
                    binding.profileBtnAdd.setVisibility(View.GONE);

                }
            }
        });

        //date picker
        binding.profileEtBirthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar now = Calendar.getInstance();

                com.wdullaer.materialdatetimepicker.date.DatePickerDialog dpd = com.wdullaer.materialdatetimepicker.date.DatePickerDialog.newInstance(new com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(com.wdullaer.materialdatetimepicker.date.DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        int Age = now.get(Calendar.YEAR) - year;
                        if (Age < 10 || Age >45){
                            Toast.makeText(ProfileActivity.this, "your not allow you to play this game", Toast.LENGTH_SHORT).show();
                        }else {
                            binding.profileEtBirthdate.setText(String.valueOf(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year));
                        }
                    }
                }, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
                dpd.show(getSupportFragmentManager(), " null ");
            }
        });
    }

    //editUnAble
    public void editUnAble() {
        binding.profileEtName.setEnabled(false);
        binding.profileEtEmail.setEnabled(false);
        binding.profileEtBirthdate.setEnabled(false);
        binding.profileRbMale.setEnabled(false);
        binding.profileRbFemale.setEnabled(false);
        binding.profileSpCountry.setEnabled(false);
    }

    //editAble
    public void editAble() {
        binding.profileEtName.setEnabled(true);
        binding.profileEtEmail.setEnabled(true);
        binding.profileEtBirthdate.setEnabled(true);
        binding.profileRbMale.setEnabled(true);
        binding.profileRbFemale.setEnabled(true);
        binding.profileSpCountry.setEnabled(true);
    }

    //Gender&Country
    public void saveSharedPreferences() {


        //Gender
        int Gender = sp.getInt(Gender_key, 3);
        if (Gender == 1) {
            binding.profileRbFemale.setChecked(true);

        } else if (Gender == 0) {
            binding.profileRbMale.setChecked(true);
        }
        binding.profileRgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.profileRbFemale) {
                    edit.putInt(Gender_key, 1);

                } else if (i == R.id.profileRbMale) {
                    edit.putInt(Gender_key, 0);
                }
                edit.apply();
            }
        });

        //Country
        String SpCountry = binding.profileSpCountry.getSelectedItem().toString();
        edit.putString("SpCountry_key", SpCountry);
        edit.apply();


    }
}























