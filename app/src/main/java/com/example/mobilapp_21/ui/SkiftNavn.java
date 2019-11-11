package com.example.mobilapp_21.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mobilapp_21.R;

public class SkiftNavn extends AppCompatActivity implements View.OnClickListener {
    Button button_skiftnavn;
    EditText editText_nytnavn;
    String navn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skiftnavn);

        button_skiftnavn = findViewById(R.id.button_nytNavn);
        button_skiftnavn.setOnClickListener(this);

        editText_nytnavn = findViewById(R.id.editText_nytNavn);
    }

    @Override
    public void onClick(View v) {
        navn = editText_nytnavn.getText().toString();
        if (v == button_skiftnavn){
            Intent intent = new Intent(this,Choose_game.class);
            intent.putExtra("SpillerNavn",navn);
            finish();
            startActivity(intent);
        }
    }
}
