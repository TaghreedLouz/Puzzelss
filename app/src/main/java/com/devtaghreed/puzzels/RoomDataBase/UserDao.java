package com.devtaghreed.puzzels.RoomDataBase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface UserDao {
    @Insert
    void InsertUser(User user);

    @Update
    void UpdateUser(User user);

    @Delete
    void DeleteUser(User user);

    @Query("SELECT * FROM User")
    LiveData<List<User>> GetAllUser();
}
