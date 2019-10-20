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


    @SuppressLint({"SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_start = findViewById(R.id.button_startSpil);
        button_start.setOnClickListener(this);

        //sætter teksten på textviewet
        TextView velkommen = findViewById(R.id.textView_velkommen);
        velkommen.setText("Velkommen til galge spillet");

        ImageView imageView_startside = findViewById(R.id.imageView_hovedmenu);
        imageView_startside.setImageResource(R.drawable.galge);
    }

    @Override
    public void onClick(View v) {

        //starter ny aktivitet
        if (v == button_start){
            Intent myIntent = new Intent(v.getContext(), Difficulty.class);
            startActivityForResult(myIntent, 0);
        }
    }
}
