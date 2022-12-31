/*package com.devtaghreed.puzzels.RoomDataBase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface PatternDao {

    @Insert
    void InsertPattern(Pattern pattern);

    @Update
    void UpdatePattern(Pattern pattern);

    @Delete
    void DeletePattern(Pattern pattern);

    @Query("SELECT * FROM Pattern ORDER BY id ASC")
    LiveData<List<Pattern>> GetAllPattern();

}
*/