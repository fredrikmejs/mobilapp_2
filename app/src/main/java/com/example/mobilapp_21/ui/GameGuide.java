package com.example.mobilapp_21.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobilapp_21.R;
import com.example.mobilapp_21.logik.Galgelogik;
import com.example.mobilapp_21.logik.LoadData;

public class GameGuide extends AppCompatActivity implements View.OnClickListener {
private Button button_back, button_clear, button_newName, button_possibleWords;
private String playerName;
private Galgelogik logic;
private LoadData loadData;
private TextView rules2, rules3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_guide);

        logic = Galgelogik.getInstance();
        loadData = LoadData.getInstance();

        playerName = loadData.getName();

        rules2 = findViewById(R.id.textView_reglerTekst2);
        rules2.setText("2. Hvis du vælger regneark,\nskal du vælge en sværhedsgrad");

        rules3 = findViewById(R.id.textView_reglerTekst3);
        rules3.setText("3. Gæt det givet ord inden for 7 \nforsøg"+
                "\n4. I mulige ord kan du selv vælge et ord");

        button_back = findViewById(R.id.button_backtoMenu);
        button_back.setOnClickListener(this);

        button_possibleWords = findViewById(R.id.button_possibleWords);
        button_possibleWords.setOnClickListener(this);

        button_newName = findViewById(R.id.button_switchName);
        button_newName.setOnClickListener(this);

        button_clear = findViewById(R.id.button_rydTopscore);
        button_clear.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == button_back) {
            Intent intent = new Intent(this, Choose_game.class);
            finish();
            startActivity(intent);
        }
        if (v == button_clear) {
            deleteCache();
            logic.clearHighscoreList();
            if(logic.getHighScoreList().size() == 0 ){
                Toast.makeText(this,"Listen er ryddet",Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(this,"Fejl listen er ikke ryddet",Toast.LENGTH_SHORT).show();
        }else if (v == button_newName){
            Intent intent = new Intent(this, SwitchName.class);
            finish();
            startActivity(intent);
        } else if(v == button_possibleWords){
            Intent intent = new Intent(this, ChooseWordList.class);
            finish();
            startActivity(intent);
        }
    }

    void deleteCache(){
        SharedPreferences sharedPreferences = getSharedPreferences("Shared", MODE_PRIVATE);
        sharedPreferences.edit().clear().apply();
        sharedPreferences.edit().putString("PlayerName", playerName).apply();
    }
}
