package com.devtaghreed.puzzels.RoomDataBase;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Query;

import java.util.List;

public class Repository {

    private LevelDao levelDao;
    private QuestionDao questionDao;
    private UserDao userDao;
    private LiveData<List<Level>> AllLevel;
    private LiveData<List<Question>> AllQuestion;
    private LiveData<List<User>> AllUser;

    public Repository(Application application) {
        MyRoomDataBase db = MyRoomDataBase.getDatabase(application);

        levelDao = db.levelDao();
        AllLevel = levelDao.GetAllLevel();

        questionDao = db.questionDao();
        AllQuestion = questionDao.GetAllQuestion();

        userDao = db.userDao();
        AllUser = userDao.GetAllUser();
    }


    //Level
    void InsertLevel(Level level) {
        MyRoomDataBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                levelDao.InsertLevel(level);
            }
        });
    }

    void UpdateLevel(Level level) {
        MyRoomDataBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                levelDao.UpdateLevel(level);
            }
        });
    }


    void DeleteLevel(Level level) {
        MyRoomDataBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                levelDao.DeleteLevel(level);

            }
        });
    }

    LiveData<List<Level>> GetAllLevel() {
        return AllLevel;
    }

//    //Pattern
//
//    void InsertPattern(Pattern pattern) {
//        MyRoomDataBase.databaseWriteExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                patternDao.InsertPattern(pattern);
//            }
//        });
//    }
//
//
//    void UpdatePattern(Pattern pattern) {
//        MyRoomDataBase.databaseWriteExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                patternDao.UpdatePattern(pattern);
//            }
//        });
//    }
//
//
//    void DeletePattern(Pattern pattern) {
//        MyRoomDataBase.databaseWriteExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                patternDao.DeletePattern(pattern);
//
//            }
//        });
//    }
//
//    LiveData<List<Pattern>> GetAllPattern() {
//        return AllPattern;
//    }
//
    //Question

    void InsertQuestion(Question question) {
        MyRoomDataBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                questionDao.InsertQuestion(question);
            }
        });
    }


    void UpdateQuestion(Question question) {
        MyRoomDataBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                questionDao.UpdateQuestion(question);
            }
        });
    }


    void DeleteQuestion(Question question) {
        MyRoomDataBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                questionDao.DeleteQuestion(question);

            }
        });
    }

    LiveData<List<Question>> GetAllQuestion() {
        return AllQuestion;
    }

    LiveData<List<Question>> GetAllQuestionById(int questionId){
        return questionDao.GetAllQuestionById(questionId);
    }

    //User

    void InsertUser(User user) {
        MyRoomDataBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userDao.InsertUser(user);
            }
        });
    }


    void UpdateUser(User user) {
        MyRoomDataBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userDao.UpdateUser(user);
            }
        });
    }


    void DeleteUser(User user) {
        MyRoomDataBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userDao.DeleteUser(user);

            }
        });
    }

    LiveData<List<User>> GetAllUser() {
        return AllUser;
    }

}
