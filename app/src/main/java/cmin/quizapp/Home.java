package cmin.quizapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    QuizViewModel viewModel = new QuizViewModel();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        if (getSupportActionBar() != null) this.getSupportActionBar().hide();

        ImageView playButton = findViewById(R.id.playbutton);
        ImageView appDescription = findViewById(R.id.appdescription);
        ImageView leaderboard = findViewById(R.id.leaderboard);

        appDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
                builder.setMessage("The environment is in a degrading state, worse than it has ever been before in human history. EcoQuiz aims to make a change by educating our future generations more about the environment and how we can help protect it. EcoQuiz is a user-friendly trivia quiz that uses a large database of questions related to protecting the environment. Keep playing and race against others to become the #1 environment protection expert!")
                        .setPositiveButton("CLOSE", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                // Create the AlertDialog object and return it
                builder.create().show();
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = viewModel.getUserName(Home.this);
                if (username != null) {
                    launchQuizScreen(username);
                } else {
                    showUsernameCreatePopup();
                }
            }
        });

        leaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Home.this, LeaderBoard.class);
                startActivity(myIntent);
            }
        });
    }

    private void launchQuizScreen(String username) {
        Context context = getApplicationContext();

        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, "Welcome " + username, duration);
        toast.show();

        Intent myIntent = new Intent(Home.this, QuizScreen.class);
        startActivity(myIntent);
    }

    private void showUsernameCreatePopup() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
        // Get the layout inflater
        LayoutInflater inflater = Home.this.getLayoutInflater();
        View layout = inflater.inflate(R.layout.enter_username, null);
        EditText usernameEditText = layout.findViewById(R.id.username_id);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(layout)
                // Add action buttons
                .setPositiveButton("enter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String username = usernameEditText.getText().toString();
                        viewModel.saveUserName(Home.this, username);
                        launchQuizScreen(username);
                    }
                });
        builder.create().show();
    }
}
