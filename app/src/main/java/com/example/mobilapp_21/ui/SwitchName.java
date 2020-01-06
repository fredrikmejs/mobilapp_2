package com.example.mobilapp_21.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mobilapp_21.R;
import com.example.mobilapp_21.logik.LoadData;

public class SwitchName extends AppCompatActivity implements View.OnClickListener {
    private Button button_switchName, button_back;
    private EditText editText_newName;
    private String name;
    private LoadData loadData;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_name);

        button_switchName = findViewById(R.id.button_nytNavn);
        button_switchName.setOnClickListener(this);

        button_back = findViewById(R.id.button_backToMenu2);
        button_back.setOnClickListener(this);

        editText_newName = findViewById(R.id.editText_nytNavn);

        loadData = LoadData.getInstance();
    }

    @Override
    public void onClick(View v) {
        name = editText_newName.getText().toString();
        if (v == button_switchName){
            if(name.equals("")){
                editText_newName.setError("Navnet er tomt");
            } else {
                loadData.setName(name);
                saveDataName();
                Intent intent = new Intent(this, Choose_game.class);
                finish();
                startActivity(intent);
            }
        } else if (v == button_back){
            Intent intent = new Intent(this,Settings.class);
            finish();
            startActivity(intent);
        }
    }


    public void saveDataName() {
        SharedPreferences sharedPreferences = getSharedPreferences("Shared", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("PlayerName", name);
        editor.apply();
    }
}
