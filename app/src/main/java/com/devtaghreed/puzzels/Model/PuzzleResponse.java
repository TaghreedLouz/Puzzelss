package com.devtaghreed.puzzels.Model;

import java.util.ArrayList;

public class PuzzleResponse {
    int level_no;
    int unlock_points;
    ArrayList<questions> questionsArrayList;

    public PuzzleResponse() {
    }

    public int getLevel_no() {
        return level_no;
    }

    public void setLevel_no(int level_no) {
        this.level_no = level_no;
    }

    public int getUnlock_points() {
        return unlock_points;
    }

    public void setUnlock_points(int unlock_points) {
        this.unlock_points = unlock_points;
    }

    public ArrayList<questions> getQuestionsArrayList() {
        return questionsArrayList;
    }

    public void setQuestionsArrayList(ArrayList<questions> questionsArrayList) {
        this.questionsArrayList = questionsArrayList;
    }

    public PuzzleResponse(int level_no, int unlock_points, ArrayList<questions> questionsArrayList) {
        this.level_no = level_no;
        this.unlock_points = unlock_points;
        this.questionsArrayList = questionsArrayList;
    }

    @Override
    public String toString() {
        return "PuzzleResponse{" +
                "level_no='" + level_no + '\'' +
                ", unlock_points='" + unlock_points + '\'' +
                ", questionsArrayList=" + questionsArrayList +
                '}';
    }
}

