package cmin.quizapp;

import android.content.res.AssetManager;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class QuizGenerator {

    public List<Quiz> quizzes;

    public void generate(AssetManager assetManager) {
        if (quizzes != null) return;

        try {
            InputStreamReader inputStreamReader = new InputStreamReader(assetManager.open("questionbank.csv"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            quizzes = new ArrayList<>();
            bufferedReader.readLine();
            String readLine = bufferedReader.readLine();
            while (readLine != null) {
                String[] row = readLine.split("\\|");
                Quiz quiz = new Quiz();
                quiz.question = row[0];
                quiz.multipleChoice1 = row[1];
                quiz.multipleChoice2 = row[2];
                quiz.multipleChoice3 = row[3];
                quiz.multipleChoice4 = row[4];
                Log.e("minmin", "row[5]: " + row[0]);
                quiz.correctChoice = Integer.parseInt(row[5]);
                quiz.explanation = row[6];
                quizzes.add(quiz);
                readLine = bufferedReader.readLine();
            }
            inputStreamReader.close();
            bufferedReader.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Quiz next() {
        int randomIndex = (int)(Math.random() * (quizzes.size() - 1));
        return quizzes.get(randomIndex);
    }
}
