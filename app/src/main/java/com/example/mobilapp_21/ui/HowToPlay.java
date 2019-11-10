package com.example.mobilapp_21.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mobilapp_21.R;

public class HowToPlay extends AppCompatActivity implements View.OnClickListener {
private Button button_back;
private String spillerNavn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play);

        Bundle lastIntent = getIntent().getExtras();
        if (lastIntent != null){
            spillerNavn = lastIntent.getString("SpillerNavn");
        }

        button_back = findViewById(R.id.button_backToMenu);
        button_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == button_back){
            Intent intent = new Intent(this,Choose_game.class);
            intent.putExtra("SpillerNavn",spillerNavn);
            finish();
            startActivity(intent);
        }
    }
}
