
package com.example.mobilapp_21.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.example.mobilapp_21.logik.Galgelogik;
import com.example.mobilapp_21.logik.LoadData;
import com.example.mobilapp_21.R;


public class Difficulty extends AppCompatActivity {

    private String diff, playerName;
    private Galgelogik logik;
    private LoadData loadData;

    String[] diffArr = {"1", "2", "3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);

        loadData = LoadData.getInstance();
        logik = Galgelogik.getInstance();

        Bundle lastIntent = getIntent().getExtras();
        if (lastIntent != null) {
            playerName = lastIntent.getString("PlayerName");
        }

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
                i.putExtra("PlayerName", playerName);
                logik.setMuligeOrd(loadData.getArk(diff));
                logik.nulstil();
                startActivity(i);

            }
        });
    }
}