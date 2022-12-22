package com.devtaghreed.puzzels.Model;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class appUtility {


    public static String readFromAssets(Context context, String fileName) {
        String string = "";
        try {
            InputStream inputStream = context.getAssets().open(fileName);
            int size = inputStream.available();
            byte[] byteObject = new byte[size];
            inputStream.read(byteObject);
            inputStream.close();
            string = new String(byteObject, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return string;
    }

    private void parsJsonFromAssets(String jsonString) {

        Log.d("parsJsonFromAssets", "parsJsonFromAssets: ");

        ArrayList<PuzzleResponse> puzzleResponseArrayList = new ArrayList<>();
        try {
            JSONArray jsonPuzzleArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonPuzzleArray.length(); i++) {
                JSONObject jsonPuzzleObject  = new JSONObject(jsonPuzzleArray.get(i).toString());
                PuzzleResponse puzzleResponse = new PuzzleResponse();
                puzzleResponse.setLevel_no(jsonPuzzleObject.getInt("level_no"));
                puzzleResponse.setUnlock_points(jsonPuzzleObject.getInt("unlock_points"));
                puzzleResponseArrayList.add(puzzleResponse);

                JSONArray questionsJsonArray = jsonPuzzleObject.getJSONArray("questions");
                ArrayList<questions> questionsArrayList = new ArrayList<>();
                for (int j = 0; j < questionsJsonArray.length(); j++) {
                    JSONObject jsonQuestionsObject = new JSONObject(questionsJsonArray.get(i).toString());

                    questions question = new questions();
                    question.setId(jsonQuestionsObject.getInt("id"));
                    question.setTitle(jsonQuestionsObject.getString("title"));
                    question.setAnswer_1(jsonQuestionsObject.getString("answer_1"));
                    question.setAnswer_2(jsonQuestionsObject.getString("answer_2"));
                    question.setAnswer_3(jsonQuestionsObject.getString("answer_3"));
                    question.setAnswer_4(jsonQuestionsObject.getString("answer_4"));
                    question.setTrue_answer(jsonQuestionsObject.getString("true_answer"));
                    question.setPoints(jsonQuestionsObject.getInt("points"));
                    question.setDuration(jsonQuestionsObject.getInt("duration"));

                    JSONObject jsonPatternObject = jsonQuestionsObject.getJSONObject("pattern");
                    Pattern pattern = new Pattern();
                    pattern.setPattern_id(jsonPatternObject.getInt("pattern_id"));
                    pattern.setPattern_name(jsonPatternObject.getString("pattern_name"));
                    question.setPattern(pattern);

                    question.setHint(jsonQuestionsObject.getString("hint"));

                    questionsArrayList.add(question);

                    puzzleResponse.setQuestionsArrayList(questionsArrayList);

                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
