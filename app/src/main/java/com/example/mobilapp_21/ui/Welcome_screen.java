package com.example.mobilapp_21.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobilapp_21.R;
import com.example.mobilapp_21.logik.Galgelogik;
import com.example.mobilapp_21.logik.LoadData;
import com.example.mobilapp_21.logik.Score;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Welcome_screen extends AppCompatActivity implements View.OnClickListener {
    public int getType = 0;
    private Button button_start;
    private EditText editText_name;
    private ArrayList<Score> highScore = new ArrayList<>();
    private Galgelogik logic;
    private String playerName;
    private ArrayList<String> sheet1 = new ArrayList<>();
    private ArrayList<String> sheet2 = new ArrayList<>();
    private ArrayList<String> sheet3 = new ArrayList<>();
    private ArrayList<String> wordDR = new ArrayList<>();
    LoadData loadData;

    @SuppressLint({"SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        //Creates an instance of Galgelogik and LoadData
        logic = Galgelogik.getInstance();
        loadData = LoadData.getInstance();

        loadDataSheet1();
        loadDataSheet2();
        loadDataSheet3();
        loadDataNameScore();

        if (highScore.size() != 0) {
            logic.setHighScoreList(highScore);
        }
        startAsyncTask();


        //Skips the frontpage if the player already have a name
        if (playerName != null) {
                Intent intent = new Intent(this, Choose_game.class);
            loadData.setName(playerName);
            saveDataName();
            finish();
            startActivity(intent);
            return;
        }

        button_start = findViewById(R.id.button_startSpil);
        button_start.setOnClickListener(this);

        editText_name = findViewById(R.id.editText_name);


        TextView welcome = findViewById(R.id.textView_velkommen);
        welcome.setText("Velkommen til galge spillet \n" +
                "Udfyld navn for at starte spillet");

        ImageView imageView_startPage = findViewById(R.id.imageView_hovedmenu);
        imageView_startPage.setImageResource(R.drawable.galge);
    }

    @Override
    public void onClick(View v) {
        playerName = editText_name.getText().toString();
        if (v == button_start) {
            if (editText_name.getText().toString().equals("")) {
                button_start.setError("UDFYLD NAVN");
            } else {
                Intent myIntent = new Intent(v.getContext(), Choose_game.class);
                playerName = editText_name.getText().toString();
                loadData.setName(playerName);
                saveDataName();
                finish();
                startActivity(myIntent);
            }
        }
    }

    private void startAsyncTask(){
        AsyncTask asyncTask = new AsyncTask();
        if (sheet1 == null|| sheet2== null|| sheet3== null){
            deleteCache();
            getType = 1;
            asyncTask.execute();

        } else{
            asyncTask.execute();
        }
    }

    private void deleteCache(){
        SharedPreferences sharedPreferences = getSharedPreferences("possibleWords", MODE_PRIVATE);
        sharedPreferences.edit().clear().apply();
    }


    private void loadDataNameScore() {
        SharedPreferences sharedPreferences = getSharedPreferences("Shared", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("HighScoreList", null);
        Type type = new TypeToken<ArrayList<Score>>() {
        }.getType();
        highScore = gson.fromJson(json, type);
        playerName = sharedPreferences.getString("PlayerName", null);
        if (highScore == null) {
            highScore = new ArrayList<>();
        }
    }

    public void saveDataName() {
        SharedPreferences sharedPreferences = getSharedPreferences("Shared", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("PlayerName", playerName);
        editor.apply();
    }

    private void loadDataSheet1() {
        SharedPreferences sharedPreferences = getSharedPreferences("possibleWords", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("sheet1", null);
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        sheet1 = gson.fromJson(json, type);
        if (sheet1 != null) {
            loadData.setSheet1(sheet1);
        }
    }

    private void loadDataSheet2() {
        SharedPreferences sharedPreferences = getSharedPreferences("possibleWords", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("sheet2", null);
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        sheet2 = gson.fromJson(json, type);
        if (sheet2 != null) {
            loadData.setSheet2(sheet2);
        }
    }

    private void loadDataSheet3() {
        SharedPreferences sharedPreferences = getSharedPreferences("possibleWords", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("sheet3", null);
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        sheet3 = gson.fromJson(json, type);
        if (sheet3 != null) {
            loadData.setSheet3(sheet3);
        }
    }




    private void saveDataSheet1() throws Exception {
        sheet1 = logic.hentOrdFraRegneark("1");
        SharedPreferences sharedPreferences = getSharedPreferences("possibleWords", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(sheet1);
        editor.putString("sheet1", json);
        editor.apply();
    }

    private void saveDataSheet2() throws Exception {
        sheet2 = logic.hentOrdFraRegneark("2");
        SharedPreferences sharedPreferences = getSharedPreferences("possibleWords", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(sheet2);
        editor.putString("sheet2", json);
        editor.apply();
    }

    private void saveDataSheet3() throws Exception {
        sheet3 = logic.hentOrdFraRegneark("3");
        SharedPreferences sharedPreferences = getSharedPreferences("possibleWords", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(sheet3);
        editor.putString("sheet3", json);
        editor.apply();
    }



    @SuppressLint("StaticFieldLeak")
    public class AsyncTask extends android.os.AsyncTask<String,String, String> {
        ProgressDialog p;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            p = new ProgressDialog(Welcome_screen.this);
            p.setMessage("Downloader ord fra DR og RegneArk");
            p.setIndeterminate(false);
            p.setCancelable(false);
            p.show();
        }
        @Override
        protected String doInBackground(String... strings) {
            try {
                if(getType == 1) {
                    saveDataSheet1();
                    loadData.setSheet1(sheet1);
                    logic.sletMuligeOrd();

                    saveDataSheet2();
                    loadData.setSheet2(sheet2);
                    logic.sletMuligeOrd();

                   saveDataSheet3();
                    loadData.setSheet3(sheet3);
                    logic.sletMuligeOrd();

                    wordDR = logic.hentOrdFraDr();
                    loadData.setWordDR(wordDR);
                }
                else  {
                    wordDR = logic.hentOrdFraDr();
                    loadData.setWordDR(wordDR);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String string) {
            super.onPostExecute(string);
                p.hide();
        }
    }
}




