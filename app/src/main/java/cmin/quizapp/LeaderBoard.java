package cmin.quizapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LeaderBoard extends AppCompatActivity {

    QuizViewModel viewModel = new QuizViewModel();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leaderboard);

        if (getSupportActionBar() != null) this.getSupportActionBar().hide();

        viewModel.updateLeaderBoardRank(this);

        viewModel.getRanks().observe(this, new Observer<List<LeaderBoardRank>>() {
            @Override
            public void onChanged(List<LeaderBoardRank> ranks) {
                RecyclerView recyclerView = findViewById(R.id.recyclerview);
                TextView rank = findViewById(R.id.rank);
                TextView username = findViewById(R.id.username);
                TextView score = findViewById(R.id.score);
                ConstraintLayout myRankLayout = findViewById(R.id.my_rank_layout);

                String myUsername = viewModel.getUserName(LeaderBoard.this);
                boolean isUserRanked = false;

                if (myUsername != null) {
                    for (int i = 0; i < ranks.size(); i++) {
                        if (ranks.get(i).username.equals(myUsername)) {
                            if (i == 0) {
                                rank.setBackgroundResource(R.drawable.rounded_corner_gold);
                            } else if (i == 1) {
                                rank.setBackgroundResource(R.drawable.rounded_corner_silver);
                            } else if (i == 2) {
                                rank.setBackgroundResource(R.drawable.rounded_corner_bronze);
                            }

                            rank.setText("" + ranks.get(i).rank);
                            username.setText(ranks.get(i).username);
                            score.setText("" + ranks.get(i).score);

                            isUserRanked = true;
                            break;
                        }
                    }

                    if (!isUserRanked) {
                        rank.setText("100+");
                        username.setText(myUsername);
                        score.setText("" + viewModel.getScore(LeaderBoard.this));
                    }

                    myRankLayout.setVisibility(View.VISIBLE);
                }

                LeaderBoardAdapter adapter = new LeaderBoardAdapter(ranks);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(LeaderBoard.this));
            }
        });
    }
}
