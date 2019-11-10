package com.example.mobilapp_21.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mobilapp_21.R;

public class HowToPlay extends AppCompatActivity implements View.OnClickListener {
private Button button_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play);

        button_back = findViewById(R.id.button_backToMenu);
        button_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == button_back){
            finish();
        }
    }
}
