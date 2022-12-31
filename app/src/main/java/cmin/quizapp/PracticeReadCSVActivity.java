package cmin.quizapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class PracticeReadCSVActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_read_csv);

        Button readButton = findViewById(R.id.readbutton);
        TextView textView = findViewById(R.id.textview);

        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                        Log.e("Question", quiz.question);
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

                    String text = "";
                    for (int i = 0; i < quizzes.size(); i++) {
                        Quiz quiz = quizzes.get(i);
                        text += "Question: " + quiz.question;
                    }

                    textView.setText(text);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
