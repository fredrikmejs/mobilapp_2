
package com.example.mobilapp_21.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


import com.example.mobilapp_21.logik.Galgelogik;
import com.example.mobilapp_21.logik.LoadData;
import com.example.mobilapp_21.R;


public class Difficulty extends AppCompatActivity implements View.OnClickListener{

    private String diff;
    private Galgelogik logik;
    private LoadData loadData;
    private Button button_back;

    String[] diffArr = {"1", "2", "3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);

        loadData = LoadData.getInstance();
        logik = Galgelogik.getInstance();

        button_back = findViewById(R.id.button_backDiff);
        button_back.setOnClickListener(this);


        ListView listView = findViewById(R.id.listview_test);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,diffArr);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                diff = diffArr[position];
                Intent i = new Intent(view.getContext(), GalgeGame.class  );
                i.putExtra("GameType", 1);
                i.putExtra("difficulty", diff);
                logik.setMuligeOrd(loadData.getSheet(diff));
                logik.nulstil();
                startActivity(i);

            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == button_back){
            Intent intent = new Intent(this, Choose_game.class);
            finish();
            startActivity(intent);
        }
    }
}