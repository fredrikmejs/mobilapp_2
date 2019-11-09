package com.example.mobilapp_21;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private String[] msværhedsgrader;
    private OnNoteListner monNoteListner;



    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(String[] sværhedsgrader, OnNoteListner onNoteListner) {
        this.msværhedsgrader = sværhedsgrader;
        this.monNoteListner = onNoteListner;
    }


    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        return new ViewHolder(view, monNoteListner);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {

        holder.sværhedsgrad.setText((msværhedsgrader[position]));
    }

    @Override
    public int getItemCount() {
        return msværhedsgrader.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView sværhedsgrad;
        OnNoteListner onNoteListner;

        public ViewHolder(@NonNull View itemView, OnNoteListner onNoteListner) {
            super(itemView);

            sværhedsgrad = itemView.findViewById(R.id.sværhedsgrad);

            this.onNoteListner = onNoteListner;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

           onNoteListner.onNoteClick(getAdapterPosition());

        }
    }

    public interface OnNoteListner{
        void onNoteClick(int position);
    }
}
