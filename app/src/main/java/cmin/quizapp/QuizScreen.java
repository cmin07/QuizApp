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

    QuizGenerator quizGenerator = new QuizGenerator();
    Quiz quiz;

    TextView streakdisplay;
    TextView scoredisplay;
    TextView quizquestion;
    Button multiplechoice1;
    Button multiplechoice2;
    Button multiplechoice3;
    Button multiplechoice4;
    TextView quizexplanation;
    Button quiznext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_screen);

        streakdisplay = findViewById(R.id.streakdisplay);
        scoredisplay = findViewById(R.id.scoredisplay);
        quizquestion = findViewById(R.id.quizquestion);
        multiplechoice1 = findViewById(R.id.multiplechoice1);
        multiplechoice2 = findViewById(R.id.multiplechoice2);
        multiplechoice3 = findViewById(R.id.multiplechoice3);
        multiplechoice4 = findViewById(R.id.multiplechoice4);
        quizexplanation = findViewById(R.id.quizexplanation);
        quiznext = findViewById(R.id.quizscreennext);

        quizGenerator.generate(getAssets());
        next();

        multiplechoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verify(quiz.correctChoice == 1);
            }
        });

        multiplechoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verify(quiz.correctChoice == 2);
            }
        });

        multiplechoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verify(quiz.correctChoice == 3);
            }
        });

        multiplechoice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verify(quiz.correctChoice == 4);
            }
        });

        quiznext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next();
            }
        });
    }

    private void next() {
        quiz = quizGenerator.next();
        quizquestion.setText(quiz.question);
        multiplechoice1.setText(quiz.multipleChoice1);
        multiplechoice2.setText(quiz.multipleChoice2);
        multiplechoice3.setText(quiz.multipleChoice3);
        multiplechoice4.setText(quiz.multipleChoice4);
        quizexplanation.setText(quiz.explanation);

        quizexplanation.setVisibility(View.GONE);
        unlockMultipleChoiceClicks();
    }

    private void verify(Boolean isCorrect) {
        if (isCorrect) {
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show();
        }
        lockMultipleChoiceClicks();
        quizexplanation.setVisibility(View.VISIBLE);
    }

    private void lockMultipleChoiceClicks() {
        multiplechoice1.setClickable(false);
        multiplechoice2.setClickable(false);
        multiplechoice3.setClickable(false);
        multiplechoice4.setClickable(false);
    }

    private void unlockMultipleChoiceClicks() {
        multiplechoice1.setClickable(true);
        multiplechoice2.setClickable(true);
        multiplechoice3.setClickable(true);
        multiplechoice4.setClickable(true);
    }
}
