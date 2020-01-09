package com.example.mobilapp_21.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobilapp_21.R;
import com.example.mobilapp_21.logik.Galgelogik;
import com.example.mobilapp_21.logik.LoadData;

public class LostScreen extends AppCompatActivity implements View.OnClickListener {

    private Button button_reset, button_menu;
    private String playerName;
    private int highScore;
    private Galgelogik logic;
    private LoadData data;
    private MediaPlayer mediaPlayer;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_screen);

        logic = Galgelogik.getInstance();
        data = LoadData.getInstance();

        Bundle lastIntent = getIntent().getExtras();
        if (lastIntent != null) {
            highScore = lastIntent.getInt("Highscore");
        }

        playerName = data.getName();

        button_menu = findViewById(R.id.button_tabtMenu);
        button_menu.setOnClickListener(this);

        button_reset = findViewById(R.id.button_nulstilTabt);
        button_reset.setOnClickListener(this);

        TextView textView_tekst = findViewById(R.id.textView_tabt);
        textView_tekst.setText("Dit ord var '" + logic.getOrdet() + "'\n" +
            "Antal brugte forsøg: " + logic.getAntalForkerteBogstaver()
        + "\n\n" + "Din Score er: " + highScore);

        //Sound taken from https://www.soundjay.com/failure-sound-effect.html
        mediaPlayer = MediaPlayer.create(this, R.raw.fail);
        mediaPlayer.start();

    }

    @Override
    public void onClick(View v) {

        if (v == button_menu){
            Intent intent = new Intent(this, Choose_game.class );
            intent.putExtra("PlayerName", playerName);
            finish();
            startActivity(intent);
        } else if (v == button_reset){
            Intent intent = new Intent(this, GalgeGame.class);
            int nulstil = 1;
            intent.putExtra("reset", nulstil);
            intent.putExtra("PlayerName", playerName);
            if (logic.getMuligeOrd().size()>0) {
                logic.nulstil();
            } else {
                Toast.makeText(this,"Sværhedsgrad sat til 1",Toast.LENGTH_LONG);
                logic.setMuligeOrd(data.getSheet("1"));
                logic.nulstil();
            }
            finish();
            startActivity(intent);
        }
    }
}