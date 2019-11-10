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
    private TextView textView_tekst;
    private String ordet;
    private ArrayList<String> brugteOrd = new ArrayList<>();
    private int antalForkerte, highscore, nulstil = 1;
    private ArrayList<String> muligeOrd = new ArrayList<>();
    private Galgelogik logik = new Galgelogik();


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_screen);


        Bundle lastIntent = getIntent().getExtras();
        if (lastIntent != null) {
            ordet = lastIntent.getString("ordet");
            brugteOrd = lastIntent.getStringArrayList("forkerte");
            antalForkerte = lastIntent.getInt("antalForkerte");
            highscore = lastIntent.getInt("Highscore");

        }

        button_menu = findViewById(R.id.button_tabtMenu);
        button_menu.setOnClickListener(this);

        button_nulstil = findViewById(R.id.button_nulstilTabt);
        button_nulstil.setOnClickListener(this);


        textView_tekst = findViewById(R.id.textView_tabt);
        textView_tekst.setText("Dit ord var '" + ordet + "'\n" +
                "der er brugt " + antalForkerte +" forsøg\n"
        + "med bogstaverne " + brugteOrd.toString() + "\n"+
                "Din score er: " + highscore);
    }

    @Override
    public void onClick(View v) {

        if (v == button_menu){
            //TODO ADD ARRAYLISTE
            Intent intent = new Intent(this, Choose_game.class );
            intent.putExtra("topscore", highscore);
            finish();
            startActivity(intent);
        }

        if (v == button_nulstil){
            Intent intent = new Intent(this, GalgeSpil.class);
            muligeOrd.addAll(logik.getMuligeOrd());
            intent.putExtra("nulstil",nulstil);
            intent.putStringArrayListExtra("muligeOrd", muligeOrd);
            finish();
            startActivity(intent);
        }

    }
}
