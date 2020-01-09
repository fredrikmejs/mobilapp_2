package com.example.mobilapp_21.logik;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mobilapp_21.R;

import java.util.ArrayList;

public class HighScoreAdapter extends RecyclerView.Adapter<HighScoreAdapter.ViewHolder> {

    private ArrayList<Score> highScore;

    public HighScoreAdapter(ArrayList<Score> highScore){
        this.highScore = highScore;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.high_score_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.score.setText("Score: " + highScore.get(position).score);
        holder.name.setText("Name: " + highScore.get(position).name);


    }

    @Override
    public int getItemCount() {
        if (highScore.size() >= 5){
            return 5;
        } else
            return highScore.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView score;
        TextView name;
        RelativeLayout rLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            score = itemView.findViewById(R.id.text_score);
            name = itemView.findViewById(R.id.text_name);
            rLayout = itemView.findViewById(R.id.recyclerView_highscore);
        }
    }
}
