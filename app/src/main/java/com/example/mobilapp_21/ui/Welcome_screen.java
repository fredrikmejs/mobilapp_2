package com.example.mobilapp_21.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobilapp_21.R;
import com.example.mobilapp_21.logik.Galgelogik;
import com.example.mobilapp_21.logik.Score;
import com.example.mobilapp_21.ui.Choose_game;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Welcome_screen extends AppCompatActivity implements View.OnClickListener {

    private Button button_start;
    private EditText editText_navn;
    private String spillerNavn;
    private ArrayList<Score> topscore = new ArrayList<>();
    private Galgelogik logik;
    private static final String pref = "topscoreListe";
    private static final String prefListe = "topscoreListe";

    @SuppressLint({"SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logik = logik.getInstance();

       // sletcache();
        loadData();
        if (topscore != null){
            logik.setHighscoreListe(topscore);
        }

        button_start = findViewById(R.id.button_startSpil);
        button_start.setOnClickListener(this);

        editText_navn = findViewById(R.id.editText_name);


        //sætter teksten på textviewet
        TextView velkommen = findViewById(R.id.textView_velkommen);
        velkommen.setText("Velkommen til galge spillet \n" +
                "Udfyld navn for at starte spillet");

        ImageView imageView_startside = findViewById(R.id.imageView_hovedmenu);
        imageView_startside.setImageResource(R.drawable.galge);
    }

    @Override
    public void onClick(View v) {
        spillerNavn = editText_navn.getText().toString();

        //starter ny aktivitet
        if (v == button_start) {
            if (editText_navn.getText().toString().equals("")) {
                button_start.setError("UDFYLD NAVN");
            } else {
                Intent myIntent = new Intent(v.getContext(), Choose_game.class);
                myIntent.putExtra("SpillerNavn", spillerNavn);
                finish();
                startActivity(myIntent);
            }
        }
    }

    void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("sharedTopscore", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("topscoreListe", null);
        Type type = new TypeToken<ArrayList<Score>>() {
        }.getType();
        topscore = gson.fromJson(json, type);

        if (topscore == null) {
            topscore = new ArrayList<>();
        }
    }


}
