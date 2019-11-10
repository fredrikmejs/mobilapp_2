package com.example.mobilapp_21.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mobilapp_21.logik.MyAdapter;
import com.example.mobilapp_21.R;

public class Difficulty extends AppCompatActivity implements MyAdapter.OnNoteListner {


    private TextView textView_current;
    private RecyclerView recyclerView_sværhedsgrad;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private String sværhedsgrad, spillerNavn;
    private ProgressBar progressBar;
    String[] sværhedsgrader = {"1","2","3"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);

        Bundle lastIntent = getIntent().getExtras();
        if (lastIntent != null) {
            spillerNavn = lastIntent.getString("SpillerNavn");
        }
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        textView_current = findViewById(R.id.textView_current);
        textView_current.setVisibility(View.INVISIBLE);

        recyclerView_sværhedsgrad = findViewById(R.id.liste_1);
        recyclerView_sværhedsgrad.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this,3);
        recyclerView_sværhedsgrad.setLayoutManager(layoutManager);


        recyclerView_sværhedsgrad.setBackgroundColor(5);
        myAdapter = new MyAdapter(sværhedsgrader,this);

        recyclerView_sværhedsgrad.setAdapter(myAdapter);
        }


    @Override
    public void onNoteClick(int position) {

        progressBar.setVisibility(View.VISIBLE);
        sværhedsgrad = sværhedsgrader[position];

        Intent i = new Intent(this, GalgeSpil.class);
        i.putExtra("GameType",1);
        i.putExtra("sværhedsgrad", sværhedsgrad);
        i.putExtra("SpillerNavn",spillerNavn);
        startActivityForResult(i,200);


    }
}
