package cmin.quizapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
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

        viewModel.updateLeaderBoardRank(this);

        viewModel.getRanks().observe(this, new Observer<List<LeaderBoardRank>>() {
            @Override
            public void onChanged(List<LeaderBoardRank> ranks) {
                RecyclerView recyclerView = findViewById(R.id.recyclerview);
                LeaderBoardAdapter adapter = new LeaderBoardAdapter(ranks);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(LeaderBoard.this));
            }
        });
    }
}
