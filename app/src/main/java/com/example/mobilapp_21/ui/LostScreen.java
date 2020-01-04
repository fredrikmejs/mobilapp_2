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

public class LostScreen extends AppCompatActivity implements View.OnClickListener {

    private Button button_reset, button_menu;
    private String playerName;
    private int highScore;
    private Galgelogik logic;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_screen);

        logic = Galgelogik.getInstance();

        Bundle lastIntent = getIntent().getExtras();
        if (lastIntent != null) {
            highScore = lastIntent.getInt("Highscore");
            playerName = lastIntent.getString("PlayerName");
        }

        button_menu = findViewById(R.id.button_tabtMenu);
        button_menu.setOnClickListener(this);

        button_reset = findViewById(R.id.button_nulstilTabt);
        button_reset.setOnClickListener(this);

        TextView textView_tekst = findViewById(R.id.textView_tabt);
        textView_tekst.setText("Dit ord var '" + logic.getOrdet() + "'\n" +
            "der er brugt " + logic.getAntalForkerteBogstaver() +" forsøg\n"
        + "med bogstaverne " + logic.getBrugteBogstaver().toString() + "\n"+
            "Din Score er: " + highScore);
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
            logic.nulstil();
            finish();
            startActivity(intent);
        }
    }
}