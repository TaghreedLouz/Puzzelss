package com.devtaghreed.puzzels.RoomDataBase;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Level {
    @PrimaryKey
    int LevelId;
    int Points;

    public Level() {
    }

    public Level(int LevelId, int points) {
        this.LevelId = LevelId;
        Points = points;
    }

    public int getLevelId() {
        return LevelId;
    }

    public void setLevelId(int levelId) {
        LevelId = levelId;
    }

    public int getPoints() {
        return Points;
    }

    public void setPoints(int points) {
        Points = points;
    }
}
