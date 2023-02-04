package cmin.quizapp;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LeaderBoardAdapter extends RecyclerView.Adapter<LeaderBoardAdapter.ViewHolder> {

    private List<LeaderBoardRank> data;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView rank;
        public final TextView username;
        public final TextView score;

        public ViewHolder(View view) {
            super(view);
            rank = (TextView) view.findViewById(R.id.rank);
            username = (TextView) view.findViewById(R.id.username);
            score = (TextView) view.findViewById(R.id.score);
        }
    }

    /**
     * Initialize the dataset of the Adapter
     *
     * @param data String[] containing the data to populate views to be used
     * by RecyclerView
     */
    public LeaderBoardAdapter(List<LeaderBoardRank> data) {
        this.data = data;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.leaderboard_adapter, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.rank.setText("" + data.get(position).rank);
        viewHolder.username.setText(data.get(position).username);
        viewHolder.score.setText("" + data.get(position).score);

        if (position == 0) {
            viewHolder.rank.setBackgroundResource(R.drawable.rounded_corner_gold);
        } else if (position == 1) {
            viewHolder.rank.setBackgroundResource(R.drawable.rounded_corner_silver);
        } else if (position == 2) {
            viewHolder.rank.setBackgroundResource(R.drawable.rounded_corner_bronze);
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return data.size();
    }
}
