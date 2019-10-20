package com.example.mobilapp_21;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class Difficulty extends AppCompatActivity implements View.OnClickListener {

    private Button button_start,button_svar, button_tilbageTilSpil;
    private EditText editText_sværhedsgrad;
    private TextView textView_sværhedsgraden;
    private String sværhedsgrad;
    private int tilbage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);

    //Tjekker om der er trykket tilbage fra galge spillet
    Bundle lastIntent = getIntent().getExtras();
        if (lastIntent != null) {
            tilbage = lastIntent.getInt("tilbage_tal");
        }

        button_tilbageTilSpil = findViewById(R.id.button_startNuværende);
    button_tilbageTilSpil.setOnClickListener(this);

        //Sætter en tilbage knap usynlig, hvis man ikke kommmer tilbage fra galgespillet
        if (tilbage != 1){
        button_tilbageTilSpil.setVisibility(View.INVISIBLE);
    }

    button_start = findViewById(R.id.button_begyndSpillet);
    button_start.setOnClickListener(this);
    button_start.setVisibility(View.INVISIBLE);

    button_svar = findViewById(R.id.button_svar);
    button_svar.setOnClickListener(this);

    editText_sværhedsgrad = findViewById(R.id.editText_sværhedsheden);

    textView_sværhedsgraden = findViewById(R.id.textView_nuværendesværhedsgrad);
    textView_sværhedsgraden.setVisibility(View.INVISIBLE);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {

        if (v == button_start){
            //Giver den valgte sværhedsgrad med videre til næste aktivitet
            Intent i = new Intent(v.getContext(), GalgeSpil.class);
            i.putExtra("sværhedsgrad", sværhedsgrad);
            startActivity(i);
        }

        if (v == button_svar){
            sværhedsgrad = editText_sværhedsgrad.getText().toString();
            //Sikre mig at der bliver svaret med et tal og ikke et bogstav
            if (sværhedsgrad.matches("[0-9]+")) {
                //sikre mig den over op til kriteret omkring 3 sværhedsgrader
                if ((Integer.valueOf(sværhedsgrad) >= 1 && Integer.valueOf(sværhedsgrad) <= 3)) {
                    textView_sværhedsgraden.setText("Sværhedsgraden er sat til " + sværhedsgrad);
                    textView_sværhedsgraden.setVisibility(View.VISIBLE);
                    button_start.setVisibility(View.VISIBLE);
                } else {
                    textView_sværhedsgraden.setText("Du skal skrive et tal mellem 1 og 3");
                    textView_sværhedsgraden.setVisibility(View.VISIBLE);
                    button_start.setVisibility(View.INVISIBLE);
                }
            } else {
                textView_sværhedsgraden.setText("Dit tal skal være et tal mellem 1 og 3");
                textView_sværhedsgraden.setVisibility(View.VISIBLE);
                button_start.setVisibility(View.INVISIBLE);
            }
        }
        //går tilbage til spillet, hvis man trykkede tilbage derfra
        if (v == button_tilbageTilSpil){
            this.finish();
        }
    }
}
