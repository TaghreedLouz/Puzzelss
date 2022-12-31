package com.devtaghreed.puzzels.RoomDataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Level.class, Question.class, User.class}, version = 1, exportSchema = false)
public abstract class MyRoomDataBase extends RoomDatabase {

    public abstract LevelDao levelDao();
   // public abstract PatternDao patternDao();
    public abstract QuestionDao questionDao();
    public abstract UserDao userDao();

    private static volatile MyRoomDataBase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static MyRoomDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MyRoomDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MyRoomDataBase.class, "MyRoomDataBase")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}