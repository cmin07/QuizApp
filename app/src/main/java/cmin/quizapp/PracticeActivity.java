package cmin.quizapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PracticeActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        Button playbutton = findViewById(R.id.playbutton);
        EditText playerName = findViewById(R.id.playerName);
        ImageView playImage = findViewById(R.id.playImage);


        playbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentPlayerName = playerName.getText().toString();
                Context context = getApplicationContext();

                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, currentPlayerName, duration);
                toast.show();
            }
        });
        playImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(PracticeActivity.this, PracticeNextActivity.class);
                startActivity(myIntent);
            }
        });

    }
}
