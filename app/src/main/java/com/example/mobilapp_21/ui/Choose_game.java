package com.example.mobilapp_21.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mobilapp_21.R;
import com.example.mobilapp_21.logik.Galgelogik;
import com.example.mobilapp_21.logik.LoadData;
import com.example.mobilapp_21.logik.Score;

import java.util.ArrayList;

public class Choose_game extends AppCompatActivity implements View.OnClickListener  {

    private Button button_settings, button_sheet, button_DR;
    private String playName;
    private Galgelogik logic;
    private ArrayList<Score> highScore = new ArrayList<>();
    private LoadData loadData;



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_game);

        logic = Galgelogik.getInstance();
        loadData = LoadData.getInstance();


        if (logic.getHighscoreListe() != null && highScore.size() == 0) {
            highScore.addAll(logic.getHighscoreListe());
        }

        Bundle lastIntent = getIntent().getExtras();
        if (lastIntent != null){
            playName = lastIntent.getString("PlayerName");
        }


        button_DR = findViewById(R.id.button_DROrd);
        button_DR.setOnClickListener(this);

        button_settings = findViewById(R.id.button_HowToPlay);
        button_settings.setOnClickListener(this);

        button_sheet = findViewById(R.id.button_regneArk);
        button_sheet.setOnClickListener(this);

        TextView textView_scoreliste = findViewById(R.id.textView_topscore);

        if (highScore.size() >= 3){
            textView_scoreliste.setText("Top 3 scorer er:\n" +
                highScore.get(0) + "\n" +
                highScore.get(1) + "\n" +
                highScore.get(2));

        } else if (highScore.size() == 2){
            textView_scoreliste.setText("Top 2 scorer er:\n" +
                highScore.get(0) + "\n" +
                highScore.get(1));
        }else if (highScore.size() == 1){
            textView_scoreliste.setText("Nuværende topscore er:\n" +
                highScore.get(0));
        }else {
            textView_scoreliste.setText("Der ikke sat nogen topscore endnu:");
        }
    }

    @Override
    public void onClick(View v) {
        logic.sletMuligeOrd();
        if (v == button_DR){
            Intent intent = new Intent(this, GalgeGame.class);
            intent.putExtra("GameType",0);
            intent.putExtra("PlayerName", playName);
            //Makes sure the Dr words is loaded
            while (loadData.getWordDR().size() == 0){}
            logic.setMuligeOrd(loadData.getWordDR());
            logic.nulstil();
            startActivity(intent);
        }else if (v == button_settings) {
            Intent intent = new Intent(this, Settings.class);
            intent.putExtra("PlayerName", playName);
            startActivity(intent);
        } else if (v == button_sheet) {
            Intent intent = new Intent(this, Difficulty.class);
            intent.putExtra("PlayerName", playName);
            startActivity(intent);
        }
    }






}
