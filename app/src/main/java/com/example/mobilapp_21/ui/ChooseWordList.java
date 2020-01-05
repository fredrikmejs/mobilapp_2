package com.example.mobilapp_21.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mobilapp_21.R;

public class ChooseWordList extends AppCompatActivity implements View.OnClickListener {
    Button button_back, bDiff1, bDiff2, bDiff3, bDr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_word_list);

    button_back = findViewById(R.id.button_listBack);
    button_back.setOnClickListener(this);

    bDiff1 = findViewById(R.id.button_diff1List);
    bDiff1.setOnClickListener(this);

    bDiff2 = findViewById(R.id.button_diff2List);
    bDiff2.setOnClickListener(this);

    bDiff3 = findViewById(R.id.button_diff3List);
    bDiff3.setOnClickListener(this);

    bDr = findViewById(R.id.button_drList);
    bDr.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == button_back){
         Intent intent = new Intent(this, Choose_game.class);
         finish();
         startActivity(intent);
        }

        if (v == bDiff1){
            Intent intent = new Intent(this, WordList.class);
            intent.putExtra("list","1");
            startActivity(intent);

        }
        if (v == bDiff2){
            Intent intent = new Intent(this, WordList.class);
            intent.putExtra("list","2");
            startActivity(intent);
        }
        if (v == bDiff3){
            Intent intent = new Intent(this, WordList.class);
            intent.putExtra("list","3");
            startActivity(intent);
        }
        if (v == bDr){
            Intent intent = new Intent(this, WordList.class);
            intent.putExtra("list","4");
            startActivity(intent);
        }
    }
}
