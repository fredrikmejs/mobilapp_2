package com.example.mobilapp_21;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

public class WonScreen extends AppCompatActivity implements View.OnClickListener {
    private Button button_nulstil, button_menu;
    private TextView textView_tekst;
    private String ordet;
    private ArrayList<String> brugteOrd = new ArrayList<>();
    private int antalForkerte, highscore;
    private ViewGroup container;
    private int nulstil =1;
    private ArrayList<String> muligeOrd = new ArrayList<>();
   // private ArrayList<Score> highscoreListe = new ArrayList<>();
    private Galgelogik logik = new Galgelogik();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_won_screen);


        Bundle lastIntent = getIntent().getExtras();
        if (lastIntent != null) {
            ordet = lastIntent.getString("ordet");
            brugteOrd = lastIntent.getStringArrayList("forkerte");
            antalForkerte = lastIntent.getInt("antalForkerte");
            highscore = lastIntent.getInt("Highscore");

        }

        button_menu = findViewById(R.id.button_vandtMenu);
        button_menu.setOnClickListener(this);

        button_nulstil = findViewById(R.id.button_nulstilVandt);
        button_nulstil.setOnClickListener(this);


        textView_tekst = findViewById(R.id.textView_vandt);
        textView_tekst.setText("Ordet var " + ordet +
                "\nDu brugte " + antalForkerte + " bogstaver \n" +
                "Din score er: " + highscore);

    }


    @Override
    public void onClick(View v) {

        if (v == button_menu){
            Intent intent = new Intent(this, Choose_game.class );intent.putExtra("nulstil",nulstil);
            intent.putExtra("topscore", highscore);
            finish();
            startActivity(intent);
        }

        if (v == button_nulstil){
            Intent intent = new Intent(this,GalgeSpil.class);
            muligeOrd.addAll(logik.getMuligeOrd());
            intent.putExtra("nulstil",nulstil);
            finish();
            startActivity(intent);
        }

    }

}
