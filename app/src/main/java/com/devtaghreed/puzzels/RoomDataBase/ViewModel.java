package com.devtaghreed.puzzels.RoomDataBase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.room.Query;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    private Repository repository;

    public ViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    //Level
    public void InsertLevel(Level level) {
        repository.InsertLevel(level);
    }

    public void UpdateLevel(Level level) {
        repository.UpdateLevel(level);
    }

    public void DeleteLevel(Level level) {
        repository.DeleteLevel(level);
    }

    public LiveData<List<Level>> GetAllLevel() {
        return repository.GetAllLevel();
    }

//    //Pattern
//    void InsertPattern(Pattern pattern) {
//        repository.InsertPattern(pattern);
//    }
//
//    void UpdatePattern(Pattern pattern) {
//        repository.UpdatePattern(pattern);
//    }
//
//    void DeletePattern(Pattern pattern) {
//        repository.DeletePattern(pattern);
//    }
//
//    LiveData<List<Pattern>> GetAllDelete() {
//        return repository.GetAllPattern();
//    }

    //Question
    public void InsertQuestion(Question question) {
        repository.InsertQuestion(question);
    }

    public void UpdateQuestion(Question question) {
        repository.UpdateQuestion(question);
    }

    public void DeleteQuestion(Question question) {
        repository.DeleteQuestion(question);
    }

    public LiveData<List<Question>> GetAllQuestion() {
        return repository.GetAllQuestion();
    }

    public LiveData<List<Question>> GetAllQuestionById(int questionId){return repository.GetAllQuestionById(questionId);}

    //User
    public void InsertUser(User user) {
        repository.InsertUser(user);
    }

    public void UpdateUser(User user) {
        repository.UpdateUser(user);
    }

    public void DeleteUser(User user) {
        repository.DeleteUser(user);
    }

    public LiveData<List<User>> GetAllUser() {
        return repository.GetAllUser();
    }

}
