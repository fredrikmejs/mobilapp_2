package com.example.mobilapp_21.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mobilapp_21.R;
import com.example.mobilapp_21.logik.Galgelogik;

public class HowToPlay extends AppCompatActivity implements View.OnClickListener {
private Button button_back, button_ryd;
private String spillerNavn;
private Galgelogik logik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play);

        logik = logik.getInstance();

        Bundle lastIntent = getIntent().getExtras();
        if (lastIntent != null){
            spillerNavn = lastIntent.getString("SpillerNavn");
        }

        button_back = findViewById(R.id.button_backToMenu);
        button_back.setOnClickListener(this);

        button_ryd = findViewById(R.id.button_rydTopscore);
        button_ryd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == button_back){
            Intent intent = new Intent(this,Choose_game.class);
            intent.putExtra("SpillerNavn",spillerNavn);
            finish();
            startActivity(intent);
        }
        if (v == button_ryd){
            sletcache();
            logik.rydHighscoreListe();
        }
    }

    void sletcache(){
        SharedPreferences sharedPreferences = getSharedPreferences("sharedTopscore", MODE_PRIVATE);
        sharedPreferences.edit().clear().apply();


    }
}
