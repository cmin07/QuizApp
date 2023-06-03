package cmin.quizapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuizScreen extends AppCompatActivity {

    QuizGenerator quizGenerator = new QuizGenerator();
    Quiz quiz;

    TextView streakdisplay;
    TextView streak;
    TextView scoredisplay;
    TextView score;
    TextView currentsessionscore;
    TextView quizquestion;
    Button multiplechoice1;
    Button multiplechoice2;
    Button multiplechoice3;
    Button multiplechoice4;
    TextView quizexplanation;
    Button quiznext;
    QuizViewModel viewModel = new QuizViewModel();
    int currentSessionScore=0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_screen);

        if (getSupportActionBar() != null) this.getSupportActionBar().hide();

        streakdisplay = findViewById(R.id.streakdisplay);
        streak = findViewById(R.id.streak);
        scoredisplay = findViewById(R.id.scoredisplay);
        score = findViewById(R.id.score);
        currentsessionscore=findViewById(R.id.curseshscore);
        quizquestion = findViewById(R.id.quizquestion);
        multiplechoice1 = findViewById(R.id.multiplechoice1);
        multiplechoice2 = findViewById(R.id.multiplechoice2);
        multiplechoice3 = findViewById(R.id.multiplechoice3);
        multiplechoice4 = findViewById(R.id.multiplechoice4);
        quizexplanation = findViewById(R.id.quizexplanation);
        quiznext = findViewById(R.id.quizscreennext);
        score.setText("" + viewModel.getScore(this));
        quizGenerator.generate(getAssets());
        next();

        multiplechoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verify(quiz.correctChoice == 1);
                if(quiz.correctChoice!=1){
                    multiplechoice1.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                }
            }
        });

        multiplechoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verify(quiz.correctChoice == 2);
                if(quiz.correctChoice!=2){
                    multiplechoice2.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                }
            }
        });

        multiplechoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verify(quiz.correctChoice == 3);
                if(quiz.correctChoice!=3){
                    multiplechoice3.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                }
            }
        });

        multiplechoice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verify(quiz.correctChoice == 4);
                if(quiz.correctChoice!=4){
                    multiplechoice4.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                }
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

        multiplechoice1.setBackgroundColor(Color.parseColor("#6200ED"));
        multiplechoice2.setBackgroundColor(Color.parseColor("#6200ED"));
        multiplechoice3.setBackgroundColor(Color.parseColor("#6200ED"));
        multiplechoice4.setBackgroundColor(Color.parseColor("#6200ED"));

        quizexplanation.setVisibility(View.GONE);
        unlockMultipleChoiceClicks();
    }

    private void verify(Boolean isCorrect) {
        if (isCorrect) {
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
            int updatedStreak = Integer.parseInt(streak.getText().toString()) + 1;
            int updatedStreakScore = (updatedStreak / 5) * 5;
            streak.setText("" + updatedStreak);

            currentSessionScore+=10+updatedStreakScore;
            currentsessionscore.setText("Current Session Score: "+currentSessionScore);

            int updatedScore = Integer.parseInt(score.getText().toString()) + 10 + updatedStreakScore;
            viewModel.saveScore(this, updatedScore);
            score.setText("" + updatedScore);
            quizexplanation.setText(
                "Correct +10\n" +
                "Streak Bonus +" + updatedStreakScore + "\n\n" +
                quiz.explanation
            );
        } else {
            Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show();
            streak.setText("0");
            quizexplanation.setText(quiz.explanation);
        }
        if(quiz.correctChoice==1){
            multiplechoice1.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
        }else if(quiz.correctChoice==2){
            multiplechoice2.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
        }else if(quiz.correctChoice==3){
            multiplechoice3.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
        }else if(quiz.correctChoice==4){
            multiplechoice4.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
        }
        lockMultipleChoiceClicks();
        quizexplanation.setVisibility(View.VISIBLE);
    }

    private void lockMultipleChoiceClicks() {
        multiplechoice1.setClickable(false);
        multiplechoice2.setClickable(false);
        multiplechoice3.setClickable(false);
        multiplechoice4.setClickable(false);
        quiznext.setClickable(true);
    }

    private void unlockMultipleChoiceClicks() {
        multiplechoice1.setClickable(true);
        multiplechoice2.setClickable(true);
        multiplechoice3.setClickable(true);
        multiplechoice4.setClickable(true);
        quiznext.setClickable(false);
    }
}
