package com.example.mobilapp_21;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button button_start;
    int highscore;

    @SuppressLint({"SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            // etc.


        setContentView(R.layout.activity_main);


        button_start = findViewById(R.id.button_startSpil);
        button_start.setOnClickListener(this);

        TextView topscore = findViewById(R.id.textView_topscore);
        topscore.setText("Din nuværende topscore er: " + highscore);

        ImageView imageView_startside = findViewById(R.id.imageView_hovedmenu);
        imageView_startside.setImageResource(R.drawable.galge);


    }

    @Override
    public void onClick(View v) {

        if (v == button_start){
            Intent myIntent = new Intent(v.getContext(), Difficulty.class);
            startActivityForResult(myIntent, 0);
        }

    }
}
