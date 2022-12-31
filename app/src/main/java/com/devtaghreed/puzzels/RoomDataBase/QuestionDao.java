package com.devtaghreed.puzzels.RoomDataBase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface QuestionDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void InsertQuestion(Question question);

    @Update
    void UpdateQuestion(Question question);

    @Delete
    void DeleteQuestion(Question question);

    @Query("SELECT * FROM Question")
    LiveData<List<Question>> GetAllQuestion();

    @Query("SELECT * FROM Question WHERE levelId = :levelId")
    LiveData<List<Question>> GetAllQuestionById(int levelId);
}


