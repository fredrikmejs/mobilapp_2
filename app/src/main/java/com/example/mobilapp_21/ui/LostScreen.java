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
import com.example.mobilapp_21.ui.Choose_game;
import com.example.mobilapp_21.ui.GalgeSpil;

import java.util.ArrayList;

public class LostScreen extends AppCompatActivity implements View.OnClickListener {

    private Button button_nulstil, button_menu;
    private String spillerNavn;
    private int highscore;
    private Galgelogik logik;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_screen);

        logik = Galgelogik.getInstance();

        Bundle lastIntent = getIntent().getExtras();
        if (lastIntent != null) {
            highscore = lastIntent.getInt("Highscore");
            spillerNavn = lastIntent.getString("SpillerNavn");
        }

        button_menu = findViewById(R.id.button_tabtMenu);
        button_menu.setOnClickListener(this);

        button_nulstil = findViewById(R.id.button_nulstilTabt);
        button_nulstil.setOnClickListener(this);

        TextView textView_tekst = findViewById(R.id.textView_tabt);
        textView_tekst.setText("Dit ord var '" + logik.getOrdet() + "'\n" +
            "der er brugt " + logik.getAntalForkerteBogstaver() +" forsøg\n"
        + "med bogstaverne " + logik.getBrugteBogstaver().toString() + "\n"+
            "Din Score er: " + highscore);
    }

    @Override
    public void onClick(View v) {

        if (v == button_menu){
            Intent intent = new Intent(this, Choose_game.class );
            intent.putExtra("SpillerNavn",spillerNavn);
            finish();
            startActivity(intent);
        } else if (v == button_nulstil){
            Intent intent = new Intent(this, GalgeSpil.class);
            int nulstil = 1;
            intent.putExtra("nulstil", nulstil);
            intent.putExtra("SpillerNavn",spillerNavn);
            logik.nulstil();
            finish();
            startActivity(intent);
        }
    }
}