package com.example.mobilapp_21.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mobilapp_21.R;

public class Choose_game extends AppCompatActivity implements View.OnClickListener  {

    private Button button_howToPlay, button_RegneArk, button_DR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_game);


        button_DR = findViewById(R.id.button_DROrd);
        button_DR.setOnClickListener(this);

        button_howToPlay = findViewById(R.id.button_HowToPlay);
        button_howToPlay.setOnClickListener(this);

        button_RegneArk = findViewById(R.id.button_regneArk);
        button_RegneArk.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v == button_DR){
            Intent intent = new Intent(this, GalgeSpil.class);
            intent.putExtra("GameType",0);
            startActivityForResult(intent,100);

        }

        if (v == button_howToPlay) {
            Intent intent = new Intent(this, HowToPlay.class);
            startActivity(intent);

        }

        if (v == button_RegneArk) {
            Intent intent = new Intent(this, Difficulty.class);
            startActivity(intent);

        }
    }
}
