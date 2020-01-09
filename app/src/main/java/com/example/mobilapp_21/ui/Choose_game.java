package com.example.mobilapp_21.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobilapp_21.R;
import com.example.mobilapp_21.logik.Galgelogik;
import com.example.mobilapp_21.logik.HighScoreAdapter;
import com.example.mobilapp_21.logik.LoadData;
import com.example.mobilapp_21.logik.RecylerAdapter;
import com.example.mobilapp_21.logik.Score;

import java.util.ArrayList;

public class Choose_game extends AppCompatActivity implements View.OnClickListener  {

    private Button button_settings, button_sheet, button_DR;
    private Galgelogik logic;
    private ArrayList<Score> highScore = new ArrayList<>();
    private LoadData loadData;
    private TextView textView_noHighScore, highScoreList;
    private RecyclerView highScoreView;



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_game);

        logic = Galgelogik.getInstance();
        loadData = LoadData.getInstance();


        if (logic.getHighScoreList() != null && highScore.size() == 0) {
            highScore.addAll(logic.getHighScoreList());
        }

        button_DR = findViewById(R.id.button_DROrd);
        button_DR.setOnClickListener(this);

        button_settings = findViewById(R.id.button_HowToPlay);
        button_settings.setOnClickListener(this);

        button_sheet = findViewById(R.id.button_regneArk);
        button_sheet.setOnClickListener(this);

        textView_noHighScore = findViewById(R.id.textView_noHighscore);
        highScoreView = findViewById(R.id.recyclerView_highscore);

        highScoreList = findViewById(R.id.highscoreList);



        if (highScore.size() > 0) {
            highScoreView.setVisibility(View.VISIBLE);
            highScoreList.setVisibility(View.VISIBLE);
            setHighScoreAdapter();
        } else {
            textView_noHighScore.setText("Der ikke sat nogen topscore endnu");
            textView_noHighScore.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        logic.sletMuligeOrd();
        if (v == button_DR){
            if(loadData.getWordDR().size() == 0){
                Toast.makeText(this,"Der er ikke hentet nogen DR ord",Toast.LENGTH_SHORT).show();
            } else {
            Intent intent = new Intent(this, GalgeGame.class);
            intent.putExtra("GameType",0);
            //Makes sure the Dr words is loaded

            logic.setMuligeOrd(loadData.getWordDR());
            logic.nulstil();
            startActivity(intent);
            }
        }else if (v == button_settings) {
            Intent intent = new Intent(this, GameGuide.class);
            startActivity(intent);
        } else if (v == button_sheet) {
            Intent intent = new Intent(this, Difficulty.class);
            startActivity(intent);
        }
    }

    private void setHighScoreAdapter(){
        RecyclerView recyclerView = findViewById(R.id.recyclerView_highscore);
        HighScoreAdapter highScoreAdapter= new HighScoreAdapter(highScore);
        recyclerView.setAdapter(highScoreAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}
