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
import com.example.mobilapp_21.logik.Score;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Choose_game extends AppCompatActivity implements View.OnClickListener  {

    private Button button_howToPlay, button_RegneArk, button_DR;
    private String spillerNavn;
    private TextView textView_scoreliste;
    private Galgelogik logik;
    private ArrayList<Score> topscore = new ArrayList<>();


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_game);

        logik = logik.getInstance();

        if (logik.getHighscoreListe() != null) {
            topscore.addAll(logik.getHighscoreListe());
        }

        Bundle lastIntent = getIntent().getExtras();
        if (lastIntent != null){
        spillerNavn = lastIntent.getString("SpillerNavn");
        }

        button_DR = findViewById(R.id.button_DROrd);
        button_DR.setOnClickListener(this);

        button_howToPlay = findViewById(R.id.button_HowToPlay);
        button_howToPlay.setOnClickListener(this);

        button_RegneArk = findViewById(R.id.button_regneArk);
        button_RegneArk.setOnClickListener(this);

        textView_scoreliste = findViewById(R.id.textView_topscore);

        if (topscore.size() >= 3){
            textView_scoreliste.setText("Top 3 scorer er:\n" +
                    topscore.get(0) + "\n" +
                    topscore.get(1) + "\n" +
                    topscore.get(2));

        } else if (topscore.size() == 2){
            textView_scoreliste.setText("Top 2 scorer er:\n" +
                    topscore.get(0) + "\n" +
                    topscore.get(1));
        }else if (topscore.size() == 1){
            textView_scoreliste.setText("Nuværende topscore er:\n" +
                    topscore.get(0));
        }else {
            textView_scoreliste.setText("Der ikke sat nogen topscore endnu:");
        }
    }

    @Override
    public void onClick(View v) {
        logik.sletMuligeOrd();
        if (v == button_DR){
            Intent intent = new Intent(this, GalgeSpil.class);
            intent.putExtra("GameType",0);
            intent.putExtra("SpillerNavn",spillerNavn);
            finish();
            startActivity(intent);
        }

        if (v == button_howToPlay) {
            Intent intent = new Intent(this, HowToPlay.class);
            finish();
            startActivity(intent);
        }

        if (v == button_RegneArk) {
            Intent intent = new Intent(this, Difficulty.class);
            intent.putExtra("SpillerNavn",spillerNavn);
            finish();
            startActivity(intent);

        }
    }

}
