package com.example.mobilapp_21.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mobilapp_21.R;

public class SwitchName extends AppCompatActivity implements View.OnClickListener {
    private Button button_switchName, button_back;
    private EditText editText_newName;
    private String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_name);

        button_switchName = findViewById(R.id.button_nytNavn);
        button_switchName.setOnClickListener(this);

        button_back = findViewById(R.id.button_backToMenu2);
        button_back.setOnClickListener(this);

        editText_newName = findViewById(R.id.editText_nytNavn);
    }

    @Override
    public void onClick(View v) {
        name = editText_newName.getText().toString();
        if (v == button_switchName){
            Intent intent = new Intent(this,Choose_game.class);
            intent.putExtra("PlayerName",name);
            finish();
            startActivity(intent);
        } else if (v == button_back){
            Intent intent = new Intent(this,Settings.class);
            intent.putExtra("PlayerName",name);
            finish();
            startActivity(intent);
        }
    }
}
