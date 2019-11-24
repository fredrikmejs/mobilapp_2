package com.example.mobilapp_21.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobilapp_21.R;
import com.example.mobilapp_21.logik.Galgelogik;
import com.example.mobilapp_21.logik.LoadData;
import com.example.mobilapp_21.logik.MyKeyboard;
import com.example.mobilapp_21.logik.Score;
import com.example.mobilapp_21.ui.Choose_game;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class Welcome_screen extends AppCompatActivity implements View.OnClickListener {


    private Button button_start;
    private EditText editText_navn;
    private ArrayList<Score> topscore = new ArrayList<>();
    private Galgelogik logik;
    private String spillerNavn;
    private ArrayList<String> ark1 = new ArrayList<>();
    private ArrayList<String> ark2 = new ArrayList<>();
    private ArrayList<String> ark3 = new ArrayList<>();
    private ArrayList<String> ordDR = new ArrayList<>();
    LoadData loadData;

    @SuppressLint({"SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Laver enstans af Galgelogikken
        logik = Galgelogik.getInstance();
        loadData = LoadData.getInstance();


        loadDataArk1();
        loadDataArk2();
        loadDataArk3();
        loadDataDR();

        loadDataNameScore();
        if (topscore != null) {
            logik.setHighscoreListe(topscore);
        }


        if (ark1 == null || ark2 == null || ark3 == null){
            sletcache();
            hentRegneArk1.start();
        }
        if (ordDR == null){
            hentDr.start();
        }




        //Springer siden over, hvis man har et spillernNavn
        if (spillerNavn != null) {
                Intent intent = new Intent(this, Choose_game.class);
            intent.putExtra("SpillerNavn", spillerNavn);
            saveDataName();
            finish();
            startActivity(intent);
            return;
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
                saveDataName();
                finish();
                startActivity(myIntent);
            }
        }
    }

    void sletcache(){
        SharedPreferences sharedPreferences = getSharedPreferences("MuligeOrd", MODE_PRIVATE);
        sharedPreferences.edit().clear().apply();
    }


    void loadDataNameScore() {
        SharedPreferences sharedPreferences = getSharedPreferences("Shared", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("topscoreListe", null);
        Type type = new TypeToken<ArrayList<Score>>() {
        }.getType();
        topscore = gson.fromJson(json, type);
        spillerNavn = sharedPreferences.getString("spillernavn", null);
        if (topscore == null) {
            topscore = new ArrayList<>();
        }
    }

    void saveDataName() {
        SharedPreferences sharedPreferences = getSharedPreferences("Shared", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("spillernavn", spillerNavn);
        editor.apply();
    }

    Thread hentRegneArk1 = new Thread() {

        public void run() {
            try {
                ark1 = logik.hentOrdFraRegneark("1");
                saveDataArk1();
                loadData.setArk1(ark1);

                logik.sletMuligeOrd();

                ark2 = logik.hentOrdFraRegneark("2");
                saveDataArk2();
                loadData.setArk2(ark2);
                logik.sletMuligeOrd();

                ark3 = logik.hentOrdFraRegneark("3");
                saveDataArk3();
                loadDataArk3();
                logik.sletMuligeOrd();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    Thread hentDr = new Thread() {

        public void run() {
            try {
                ordDR =logik.hentOrdFraDr();
                saveDataDr();
                loadData.setOrdDR(ordDR);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };


    void loadDataArk1() {
        SharedPreferences sharedPreferences = getSharedPreferences("MuligeOrd", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Ark1", null);
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        ark1 = gson.fromJson(json, type);
        if (ark1 != null) {
            loadData.setArk1(ark1);
        }
    }

    void loadDataArk2() {
        SharedPreferences sharedPreferences = getSharedPreferences("MuligeOrd", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Ark2", null);
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        ark2 = gson.fromJson(json, type);
        if (ark2 != null) {
            loadData.setArk2(ark2);
        }
    }

    void loadDataArk3() {
        SharedPreferences sharedPreferences = getSharedPreferences("MuligeOrd", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Ark3", null);
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        ark3 = gson.fromJson(json, type);
        if (ark3 != null) {
            loadData.setArk3(ark3);
        }
    }

    void loadDataDR() {
        SharedPreferences sharedPreferences = getSharedPreferences("MuligeOrd", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("DRord", null);
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        ordDR = gson.fromJson(json, type);
        if (ordDR != null) {
            loadData.setOrdDR(ordDR);
        }
    }


    void saveDataArk1() throws Exception {
        ark1 = logik.hentOrdFraRegneark("1");
        SharedPreferences sharedPreferences = getSharedPreferences("MuligeOrd", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(ark1);
        editor.putString("Ark1", json);
        editor.apply();
    }

    void saveDataArk2() throws Exception {
        ark2 = logik.hentOrdFraRegneark("2");
        SharedPreferences sharedPreferences = getSharedPreferences("MuligeOrd", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(ark2);
        editor.putString("Ark2", json);
        editor.apply();
    }

    void saveDataArk3() throws Exception {
        ark3 = logik.hentOrdFraRegneark("3");
        SharedPreferences sharedPreferences = getSharedPreferences("MuligeOrd", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(ark3);
        editor.putString("Ark3", json);
        editor.apply();
    }

    void saveDataDr() throws Exception {
        ordDR = logik.hentOrdFraDr();
        SharedPreferences sharedPreferences = getSharedPreferences("MuligeOrd", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(ordDR);
        editor.putString("DRord", json);
        editor.apply();
    }
}
