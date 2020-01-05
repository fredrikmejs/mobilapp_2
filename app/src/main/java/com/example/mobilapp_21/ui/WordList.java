package com.example.mobilapp_21.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mobilapp_21.R;
import com.example.mobilapp_21.logik.LoadData;
import com.example.mobilapp_21.logik.RecylerAdapter;

import java.util.ArrayList;

public class WordList extends AppCompatActivity implements View.OnClickListener {
private String listType;
private ArrayList<String> list = new ArrayList<>();
private LoadData loadData;
private Button button_back;
private TextView headline;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);

        loadData = LoadData.getInstance();

        button_back = findViewById(R.id.button_goBackList);
        button_back.setOnClickListener(this);

        headline = findViewById(R.id.ListType);


        Bundle lastIntent = getIntent().getExtras();
        if (lastIntent != null) {
            if (lastIntent.getString("list") != null)
                listType = lastIntent.getString("list");
             }

        if (!listType.equals("4")){
            list.addAll(loadData.getSheet(listType));
            headline.setText("Ord fra sværhedsgrad " + listType);
        } else {
            list.addAll(loadData.getWordDR());
            headline.setText("Ord fra Dr");
        }

        initRecylerView();

    }

    private void initRecylerView(){
        RecyclerView recyclerView = findViewById(R.id.recyleview);
        RecylerAdapter recylerAdapter = new RecylerAdapter(list,this);
        recyclerView.setAdapter(recylerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onClick(View v) {
        if(v == button_back){
            Intent intent = new Intent(this,Choose_game.class);
            finish();
            startActivity(intent);
        }
    }
}
