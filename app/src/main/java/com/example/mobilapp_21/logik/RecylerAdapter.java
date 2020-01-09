package com.example.mobilapp_21.logik;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilapp_21.R;
import com.example.mobilapp_21.ui.GalgeGame;

import java.util.ArrayList;

public class RecylerAdapter extends RecyclerView.Adapter<RecylerAdapter.ViewHolder> {

    private ArrayList<String> words;
    private Galgelogik logic;
    private Context mcontext;

    public RecylerAdapter(ArrayList<String> words,Context context){
        this.words = words;
        this.mcontext = context;
        logic = Galgelogik.getInstance();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyle_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.wordTextView.setText(words.get(position));

        holder.rLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logic.setOrdet(words.get(position));
                Intent intent = new Intent(mcontext, GalgeGame.class);
                mcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView wordTextView;
        RelativeLayout rLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            wordTextView = itemView.findViewById(R.id.text_Recyleviw);
            rLayout = itemView.findViewById(R.id.rLayout);
        }
    }
}
