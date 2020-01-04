package com.example.mobilapp_21.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mobilapp_21.R;
import com.example.mobilapp_21.logik.Galgelogik;

public class Settings extends AppCompatActivity implements View.OnClickListener {
private Button button_back, button_clear, button_newName;
private String playerName;
private Galgelogik logic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        logic = Galgelogik.getInstance();

        Bundle lastIntent = getIntent().getExtras();
        if (lastIntent != null){
            playerName = lastIntent.getString("PlayerName");
        }

        button_back = findViewById(R.id.button_backToMenu);
        button_back.setOnClickListener(this);

        button_newName = findViewById(R.id.button_switchName);
        button_newName.setOnClickListener(this);

        button_clear = findViewById(R.id.button_rydTopscore);
        button_clear.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == button_back) {
            Intent intent = new Intent(this, Choose_game.class);
            intent.putExtra("PlayerName", playerName);
            finish();
            startActivity(intent);
        }
        if (v == button_clear) {
            deleteCache();
            logic.rydHighscoreListe();
        }else if (v == button_newName){
            Intent intent = new Intent(this, SwitchName.class);
            intent.putExtra("Playername",playerName);
            startActivity(intent);
        }
    }

    void deleteCache(){
        SharedPreferences sharedPreferences = getSharedPreferences("Shared", MODE_PRIVATE);
        sharedPreferences.edit().clear().apply();
        sharedPreferences.edit().putString("PlayerName", playerName).apply();
    }
}
