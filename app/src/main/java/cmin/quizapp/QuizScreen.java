package cmin.quizapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class QuizScreen extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_screen);

        TextView streakdisplay = findViewById(R.id.streakdisplay);
        TextView scoredisplay = findViewById(R.id.scoredisplay);
        TextView quizquestion = findViewById(R.id.quizquestion);
        Button multiplechoice1 = findViewById(R.id.multiplechoice1);
        Button multiplechoice2 = findViewById(R.id.multiplechoice2);
        Button multiplechoice3 = findViewById(R.id.multiplechoice3);
        Button multiplechoice4 = findViewById(R.id.multiplechoice4);
        TextView quizexplanation = findViewById(R.id.quizexplanation);
        Button quiznext = findViewById(R.id.quizscreennext);

        try {
            InputStreamReader inputStreamReader = new InputStreamReader(getAssets().open("questionbank.csv"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            List<Quiz> quizzes = new ArrayList<>();
            bufferedReader.readLine();
            String readLine = bufferedReader.readLine();
            while (readLine != null) {
                String[] row = readLine.split(",");
                Quiz quiz = new Quiz();
                quiz.question = row[0];
                quiz.multipleChoice1 = row[1];
                quiz.multipleChoice2 = row[2];
                quiz.multipleChoice3 = row[3];
                quiz.multipleChoice4 = row[4];
                quiz.correctChoice = Integer.parseInt(row[5]);
                quiz.explanation = row[6];
                quizzes.add(quiz);
                readLine = bufferedReader.readLine();
            }
            inputStreamReader.close();
            bufferedReader.close();
            quizquestion.setText(quizzes.get(0).question);
            multiplechoice1.setText(quizzes.get(0).multipleChoice1);
            multiplechoice2.setText(quizzes.get(0).multipleChoice2);
            multiplechoice3.setText(quizzes.get(0).multipleChoice3);
            multiplechoice4.setText(quizzes.get(0).multipleChoice4);
            quizexplanation.setText(quizzes.get(0).explanation);
            quizexplanation.setVisibility(View.GONE);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
