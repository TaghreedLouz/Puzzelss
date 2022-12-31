package com.devtaghreed.puzzels.Model;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.devtaghreed.puzzels.RoomDataBase.Level;
import com.devtaghreed.puzzels.RoomDataBase.Question;
import com.devtaghreed.puzzels.RoomDataBase.ViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class parsJson {
    Context context;
    ViewModel viewModel;

    public parsJson(Context context) {
        this.context = context;
    }

    //pars Level
    public void parsJsonFromAssetsForLevel(String jsonString) {
        viewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(ViewModel.class);
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int u = 0; u < jsonArray.length(); u++) {
                JSONObject jsonObject = jsonArray.getJSONObject(u);
                int level_no = jsonObject.getInt("level_no");
                int unlock_points = jsonObject.getInt("unlock_points");
                Level level = new Level(level_no, unlock_points);
                viewModel.InsertLevel(level);

                JSONArray questionJsonArray = jsonObject.getJSONArray("questions");

            }
            parsJsonFromAssetsForQuestions(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("parsJsonFromAssets", "parsJsonFromAssets: " + e.getMessage());
        }
    }

    //pars Question
    public void parsJsonFromAssetsForQuestions(String jsonString) {
        viewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(ViewModel.class);
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int u = 0; u < jsonArray.length(); u++) {
                JSONObject jsonObject = jsonArray.getJSONObject(u);
                int level_no = jsonObject.getInt("level_no");

                JSONArray questionJsonArray = jsonObject.getJSONArray("questions");
                for (int i = 0; i < questionJsonArray.length(); i++) {
                    JSONObject questionJsonObject = questionJsonArray.getJSONObject(i);
                    int id = questionJsonObject.getInt("id");
                    String title = questionJsonObject.getString("title");
                    String answer_1 = questionJsonObject.getString("answer_1");
                    String answer_2 = questionJsonObject.getString("answer_2");
                    String answer_3 = questionJsonObject.getString("answer_3");
                    String answer_4 = questionJsonObject.getString("answer_4");
                    String true_answer = questionJsonObject.getString("true_answer");
                    int points = questionJsonObject.getInt("points");
                    int duration = questionJsonObject.getInt("duration");
                    String hint = questionJsonObject.getString("hint");
                    JSONObject patternJsonObject = questionJsonObject.getJSONObject("pattern");
                    int pattern_id = patternJsonObject.getInt("pattern_id");
                    String pattern_name = patternJsonObject.getString("pattern_name");
                    Question question = new Question(id, title, answer_1, answer_2, answer_3, answer_4, true_answer, points, level_no, duration, pattern_name, pattern_id, hint);
                    viewModel.InsertQuestion(question);

                    Log.d("title ", title);
                    Log.d("true_answer ", true_answer);
                    Log.d("hint ", hint);

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("parsJsonFromAssets", "parsJsonFromAssets: " + e.getMessage());
        }
    }

    //readFromAssets
    public static String readFromAssets(Context context) {
        String json = "";
        try {
            InputStream inputStream = context.getAssets().open("puzzleGameData.json");
            int size = inputStream.available();
            byte[] byteObject = new byte[size];
            inputStream.read(byteObject);
            inputStream.close();
            json = new String(byteObject, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;

    }
}
