package com.example.mobilapp_21.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobilapp_21.R;
import com.example.mobilapp_21.logik.Galgelogik;
import com.example.mobilapp_21.logik.LoadData;
import com.example.mobilapp_21.logik.Score;

import java.util.ArrayList;

public class Choose_game extends AppCompatActivity implements View.OnClickListener  {

    private Button button_settings, button_sheet, button_DR;
    private Galgelogik logic;
    private ArrayList<Score> highScore = new ArrayList<>();
    private LoadData loadData;
    private TextView textView_highScoreList;



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

        button_DR = findViewById(R.id.button_DROrd);
        button_DR.setOnClickListener(this);

        button_settings = findViewById(R.id.button_HowToPlay);
        button_settings.setOnClickListener(this);

        button_sheet = findViewById(R.id.button_regneArk);
        button_sheet.setOnClickListener(this);

        textView_highScoreList = findViewById(R.id.textView_topscore);

        setHighscoreList();

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
            Intent intent = new Intent(this, Settings.class);
            startActivity(intent);
        } else if (v == button_sheet) {
            Intent intent = new Intent(this, Difficulty.class);
            startActivity(intent);
        }
    }

    @SuppressLint("SetTextI18n")
    private void setHighscoreList(){
        if (highScore.size() >= 5){
            textView_highScoreList.setText("Top 5 scorere er:\n" +
                    highScore.get(0) + "\n" +
                    highScore.get(1) + "\n" +
                    highScore.get(2)+ "\n" +
                    highScore.get(3) + "\n" +
                    highScore.get(4)+ "\n");

        } else if (highScore.size() == 4) {
            textView_highScoreList.setText("Top 4 scorer er:\n" +
                    highScore.get(0) + "\n" +
                    highScore.get(1) + "\n" +
                    highScore.get(2)+ "\n" +
                    highScore.get(3) + "\n");

        } else if (highScore.size() == 3) {
            textView_highScoreList.setText("Top 3 scorer er:\n" +
                    highScore.get(0) + "\n" +
                    highScore.get(1) + "\n" +
                    highScore.get(2));

        } else if (highScore.size() == 2) {
            textView_highScoreList.setText("Top 2 scorer er:\n" +
                    highScore.get(0) + "\n" +
                    highScore.get(1));
        } else if (highScore.size() == 1) {
            textView_highScoreList.setText("Nuværende topscore er:\n" +
                    highScore.get(0));
        } else {
            textView_highScoreList.setText("Der ikke sat nogen topscore endnu:");
        }
    }






}
