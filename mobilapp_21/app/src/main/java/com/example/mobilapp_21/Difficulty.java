package com.example.mobilapp_21;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Difficulty extends AppCompatActivity implements View.OnClickListener {

    private Button button_start,button_svar;
    private EditText editText_sværhedsgrad;
    private TextView textView_sværhedsgraden;
    private Editable sværhedsgrad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);

    button_start = findViewById(R.id.button_begyndSpillet);
    button_start.setOnClickListener(this);

    button_svar = findViewById(R.id.button_svar);
    button_svar.setOnClickListener(this);

    editText_sværhedsgrad = findViewById(R.id.editText_sværhedsheden);

    textView_sværhedsgraden = findViewById(R.id.textView_nuværendesværhedsgrad);



    }


    @Override
    public void onClick(View v) {

        if (v == button_start){
            Intent i = new Intent(v.getContext(), Difficulty.class);
            i.putExtra("sværhedsgrad", sværhedsgrad);
            startActivity(i);
        }

        if (v == button_svar){
            sværhedsgrad = editText_sværhedsgrad.getText();

            if (Integer.valueOf(String.valueOf(sværhedsgrad))>= 1 || Integer.valueOf(String.valueOf(sværhedsgrad)) <= 12){
                textView_sværhedsgraden.setText("Sværhedsgraden er sat til" + sværhedsgrad);
            } else editText_sværhedsgrad.setError("Skriv et tal mellem 1 og 12");

        }



    }
}
