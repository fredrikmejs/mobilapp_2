package com.example.mobilapp_21.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mobilapp_21.R;
import com.example.mobilapp_21.logik.Galgelogik;
import com.example.mobilapp_21.logik.LoadData;

public class Settings extends AppCompatActivity implements View.OnClickListener {
private Button button_back, button_clear, button_newName, button_possibleWords;
private String playerName;
private Galgelogik logic;
private LoadData loadData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        logic = Galgelogik.getInstance();
        loadData = LoadData.getInstance();

        playerName = loadData.getName();


        button_back = findViewById(R.id.button_backtoMenu);
        button_back.setOnClickListener(this);

        button_possibleWords = findViewById(R.id.button_possibleWords);
        button_possibleWords.setOnClickListener(this);

        button_newName = findViewById(R.id.button_switchName);
        button_newName.setOnClickListener(this);

        button_clear = findViewById(R.id.button_rydTopscore);
        button_clear.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == button_back) {
            Intent intent = new Intent(this, Choose_game.class);

            finish();
            startActivity(intent);
        }
        if (v == button_clear) {
            deleteCache();
            logic.rydHighscoreListe();
        }else if (v == button_newName){
            Intent intent = new Intent(this, SwitchName.class);
            startActivity(intent);
        } else if(v == button_possibleWords){
            Intent intent = new Intent(this, ChooseWordList.class);
            finish();
            startActivity(intent);
        }
    }

    void deleteCache(){
        SharedPreferences sharedPreferences = getSharedPreferences("Shared", MODE_PRIVATE);
        sharedPreferences.edit().clear().apply();
        sharedPreferences.edit().putString("PlayerName", playerName).apply();
    }
}
