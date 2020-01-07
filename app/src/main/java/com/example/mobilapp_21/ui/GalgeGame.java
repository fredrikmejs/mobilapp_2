package com.example.mobilapp_21.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobilapp_21.R;
import com.example.mobilapp_21.logik.Galgelogik;
import com.example.mobilapp_21.logik.LoadData;
import com.google.gson.Gson;

import java.util.ArrayList;

public class GalgeGame extends AppCompatActivity implements View.OnClickListener {
    Galgelogik logic;
    private Button button_back, button_reset, button_a, button_b, button_c, button_d, button_e, button_f, button_g,
            button_h, button_i, button_j, button_k, button_l, button_m, button_n, button_o, button_p, button_q, button_r, button_s,
            button_t, button_u, button_v, button_x, button_y, button_z, button_æ, button_ø, button_å;
    private TextView textView_secretWord;
    private String dfficulty, playerName;
    private ImageView imageView_game;
    private int gameType;
    private int reset = 0;
    LoadData data;
    private ArrayList<String> possibleWords = new ArrayList<>();


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galge_spil);
        logic = Galgelogik.getInstance();
        data = LoadData.getInstance();

        //Uses the difficulty from Difficulty
        Bundle lastIntent = getIntent().getExtras();
        if (lastIntent != null) {
            if (lastIntent.getString("difficulty") != null)
                dfficulty = lastIntent.getString("difficulty");
            if (lastIntent.getInt("reset") != 0)
                reset = lastIntent.getInt("nulstil");
            gameType = lastIntent.getInt("GameType");
        }


        playerName = data.getName();

        identifyButtons();



        imageView_game = findViewById(R.id.imageView_spil);

        graphic();

        textView_secretWord = findViewById(R.id.textView_hemmeligtOrd);
        textView_secretWord.setText("Gæt ordet   " + logic.getSynligtOrd());
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {


         if (v == button_back){
            Intent intent = new Intent(GalgeGame.this, Choose_game.class);
            finish();
            startActivity(intent);
        } else if (v == button_reset){
            reset = 1;
            logic.nulstil();
            possibleWords.addAll(logic.getMuligeOrd());
            Intent intent = getIntent();
            intent.putExtra("reset",reset);
            intent.putExtra("difficulty",dfficulty);
            intent.putExtra("GameType", gameType);
            intent.putExtra("PlayerName", playerName);
            finish();
            startActivity(intent);
        } else if (v == button_a){
            logic.gætBogstav("a");
            button_a.setEnabled(false);
            button_a.setBackgroundColor(Color.parseColor("#ed5440"));
            button_a.setTextColor(Color.parseColor("#1a1818"));
            updateText();
            graphic();
        }else if (v == button_b){
            logic.gætBogstav("b");
            button_b.setEnabled(false);
            button_b.setBackgroundColor(Color.parseColor("#ed5440"));
            button_b.setTextColor(Color.parseColor("#1a1818"));
            updateText();
            graphic();
        }else if (v == button_c){
            logic.gætBogstav("c");
            button_c.setEnabled(false);
            button_c.setBackgroundColor(Color.parseColor("#ed5440"));
            button_c.setTextColor(Color.parseColor("#1a1818"));
            updateText();
            graphic();
        }else if (v == button_d){
            logic.gætBogstav("d");
            button_d.setEnabled(false);
            button_d.setBackgroundColor(Color.parseColor("#ed5440"));
            button_d.setTextColor(Color.parseColor("#1a1818"));
            updateText();
            graphic();
        }else if (v == button_e){
            logic.gætBogstav("e");
            button_e.setEnabled(false);
            button_e.setBackgroundColor(Color.parseColor("#ed5440"));
            button_e.setTextColor(Color.parseColor("#1a1818"));
            updateText();
            graphic();
        }else if (v == button_f){
            logic.gætBogstav("f");
            button_f.setEnabled(false);
            button_f.setBackgroundColor(Color.parseColor("#ed5440"));
            button_f.setTextColor(Color.parseColor("#1a1818"));
            updateText();
            graphic();
        }else if (v == button_g){
            logic.gætBogstav("g");
            button_g.setEnabled(false);
            button_g.setBackgroundColor(Color.parseColor("#ed5440"));
            button_g.setTextColor(Color.parseColor("#1a1818"));
            updateText();
            graphic();
        }else if (v == button_h){
            logic.gætBogstav("h");
            button_h.setEnabled(false);
            button_h.setBackgroundColor(Color.parseColor("#ed5440"));
            button_h.setTextColor(Color.parseColor("#1a1818"));
            updateText();
            graphic();
        }else if (v == button_i){
            logic.gætBogstav("i");
            button_i.setEnabled(false);
            button_i.setBackgroundColor(Color.parseColor("#ed5440"));
            button_i.setTextColor(Color.parseColor("#1a1818"));
            updateText();
            graphic();
        }else if (v == button_j){
            logic.gætBogstav("j");
            button_j.setEnabled(false);
            button_j.setBackgroundColor(Color.parseColor("#ed5440"));
            button_j.setTextColor(Color.parseColor("#1a1818"));
            updateText();
            graphic();
        }else if (v == button_k){
            logic.gætBogstav("k");
            button_k.setEnabled(false);
            button_k.setBackgroundColor(Color.parseColor("#ed5440"));
            button_k.setTextColor(Color.parseColor("#1a1818"));
            updateText();
            graphic();
        }else if (v == button_l){
            logic.gætBogstav("l");
            button_l.setEnabled(false);
            button_l.setBackgroundColor(Color.parseColor("#ed5440"));
            button_l.setTextColor(Color.parseColor("#1a1818"));
            updateText();
            graphic();
        }else if (v == button_m){
            logic.gætBogstav("m");
            button_m.setEnabled(false);
            button_m.setBackgroundColor(Color.parseColor("#ed5440"));
            button_m.setTextColor(Color.parseColor("#1a1818"));
            updateText();
            graphic();
        }else if (v == button_n){
            logic.gætBogstav("n");
            button_n.setEnabled(false);
            button_n.setBackgroundColor(Color.parseColor("#ed5440"));
            button_n.setTextColor(Color.parseColor("#1a1818"));
            updateText();
            graphic();
        }else if (v == button_o){
            logic.gætBogstav("o");
            button_o.setEnabled(false);
            button_o.setBackgroundColor(Color.parseColor("#ed5440"));
            button_o.setTextColor(Color.parseColor("#1a1818"));
            updateText();
            graphic();
        }else if (v == button_p){
            logic.gætBogstav("p");
            button_p.setEnabled(false);
            button_p.setBackgroundColor(Color.parseColor("#ed5440"));
            button_p.setTextColor(Color.parseColor("#1a1818"));
            updateText();
            graphic();
        }else if (v == button_q){
            logic.gætBogstav("q");
            button_q.setEnabled(false);
            button_q.setBackgroundColor(Color.parseColor("#ed5440"));
            button_q.setTextColor(Color.parseColor("#1a1818"));
            updateText();
            graphic();
        }else if (v == button_r){
            logic.gætBogstav("r");
            button_r.setEnabled(false);
            button_r.setBackgroundColor(Color.parseColor("#ed5440"));
            button_r.setTextColor(Color.parseColor("#1a1818"));
            updateText();
            graphic();
        }else if (v == button_s){
            logic.gætBogstav("s");
            button_s.setEnabled(false);
            button_s.setBackgroundColor(Color.parseColor("#ed5440"));
            button_s.setTextColor(Color.parseColor("#1a1818"));
            updateText();
            graphic();
        }else if (v == button_t){
            logic.gætBogstav("t");
            button_t.setEnabled(false);
            button_t.setBackgroundColor(Color.parseColor("#ed5440"));
            button_t.setTextColor(Color.parseColor("#1a1818"));
            updateText();
            graphic();
        }else if (v == button_u){
            logic.gætBogstav("u");
            button_u.setEnabled(false);
            button_u.setBackgroundColor(Color.parseColor("#ed5440"));
            button_u.setTextColor(Color.parseColor("#1a1818"));
            updateText();
            graphic();
        }else if (v == button_v){
            logic.gætBogstav("v");
            button_v.setEnabled(false);
            button_v.setBackgroundColor(Color.parseColor("#ed5440"));
            button_v.setTextColor(Color.parseColor("#1a1818"));
            updateText();
            graphic();
        }else if (v == button_x){
            logic.gætBogstav("x");
            button_x.setEnabled(false);
            button_x.setBackgroundColor(Color.parseColor("#ed5440"));
            button_x.setTextColor(Color.parseColor("#1a1818"));
            updateText();
            graphic();
        }else if (v == button_y){
            logic.gætBogstav("y");
            button_y.setEnabled(false);
            button_y.setBackgroundColor(Color.parseColor("#ed5440"));
            button_y.setTextColor(Color.parseColor("#1a1818"));
            updateText();
            graphic();
         }else if (v == button_z){
            logic.gætBogstav("z");
            button_z.setEnabled(false);
            button_z.setBackgroundColor(Color.parseColor("#ed5440"));
            button_z.setTextColor(Color.parseColor("#1a1818"));
            updateText();
            graphic();
        }else if (v == button_æ){
            logic.gætBogstav("æ");
            button_æ.setEnabled(false);
            button_æ.setBackgroundColor(Color.parseColor("#ed5440"));
            button_æ.setTextColor(Color.parseColor("#1a1818"));
            updateText();
            graphic();
        }else if (v == button_ø){
            logic.gætBogstav("ø");
            button_ø.setEnabled(false);
            button_ø.setBackgroundColor(Color.parseColor("#ed5440"));
            button_ø.setTextColor(Color.parseColor("#1a1818"));
            updateText();
            graphic();
        }else if (v == button_å){
            logic.gætBogstav("å");
            button_å.setEnabled(false);
            button_å.setBackgroundColor(Color.parseColor("#ed5440"));
            button_å.setTextColor(Color.parseColor("#1a1818"));
            updateText();
            graphic();
        }
    }

    /**
     * Updates the text after a guess
     */
    @SuppressLint("SetTextI18n")
    private void updateText() {
        textView_secretWord.setText("Gæt ordet   " + logic.getSynligtOrd()
        );

        if (logic.erSpilletVundet()) {
            int highScore = calculateScore();
            int mistakes = logic.getAntalForkerteBogstaver();
            logic.setHighScoreListe(playerName, highScore);
            saveData();
            Intent intent = new Intent(this, WonScreen.class);
            intent.putExtra("Highscore",highScore);
            intent.putExtra("mistakes",mistakes);
            finish();
            startActivity(intent);
        }
        if (logic.erSpilletTabt()) {
            int highscore = calculateScore();
            logic.setHighScoreListe(playerName, highscore);
            saveData();
            Intent intent = new Intent(this, LostScreen.class);
            intent.putExtra("Highscore",highscore);
            finish();
            startActivity(intent);
        }
    }

    public int calculateScore() {
        int score = 1000;
        boolean isWon = logic.erSpilletVundet(), erTabt = logic.erSpilletTabt();
        int wrongs = logic.getAntalForkerteBogstaver();
        int numberCorrects = logic.getAntalKorrekte();

        if ((gameType == 0 && isWon) || (gameType == 1 && dfficulty.equals("3")) && isWon) {
            score = 1000 - wrongs * 50;
            return score;
        } else if (gameType == 1 && dfficulty.equals("2") && isWon) {
            score = 1000 - 200 - (wrongs * 50);
            return score;
        } else if (gameType == 1 && dfficulty.equals("1") && isWon) {
            score = 1000 - 400 - (wrongs * 50);
            return score;
        } else if (gameType == 0 || (gameType == 1 && dfficulty.equals("3")) && erTabt) {
            score = numberCorrects*65;
            return score;
        } else if (gameType == 1 && dfficulty.equals("2") && erTabt) {
            score = numberCorrects*45;
            return score;
        } else if (gameType == 1 && dfficulty.equals("1") && erTabt) {
            score = numberCorrects*35;
            return score;
        }
        return score;
    }

    /**
     * Saves data in cache
     */
    void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("Shared",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(logic.getHighscoreListe());
        editor.putString("HighScoreList",json);
        editor.apply();
    }

    /**
     * Switches picture depending on number of wrong answers
     */
    private void graphic(){
       int wrongs = logic.getAntalForkerteBogstaver();
        switch (wrongs){
            case 0:
                imageView_game.setImageResource(R.drawable.galge);
                break;
            case 1:
                imageView_game.setImageResource(R.drawable.forkert1);
                break;
            case 2:
                imageView_game.setImageResource(R.drawable.forkert2);
                break;
            case 3:
                imageView_game.setImageResource(R.drawable.forkert3);
                break;
            case 4:
                imageView_game.setImageResource(R.drawable.forkert4);
                break;
            case 5:
                imageView_game.setImageResource(R.drawable.forkert5);
                break;
            case 6:
                imageView_game.setImageResource(R.drawable.forkert6);
                break;
        }
    }

    private void identifyButtons(){

        button_a = findViewById(R.id.button_a);
        button_a.setOnClickListener(this);

        button_b = findViewById(R.id.button_b);
        button_b.setOnClickListener(this);

        button_c = findViewById(R.id.button_c);
        button_c.setOnClickListener(this);

        button_d = findViewById(R.id.button_d);
        button_d.setOnClickListener(this);

        button_e = findViewById(R.id.button_e);
        button_e.setOnClickListener(this);

        button_f = findViewById(R.id.button_f);
        button_f.setOnClickListener(this);

        button_g = findViewById(R.id.button_g);
        button_g.setOnClickListener(this);

        button_h = findViewById(R.id.button_h);
        button_h.setOnClickListener(this);

        button_i = findViewById(R.id.button_i);
        button_i.setOnClickListener(this);

        button_j = findViewById(R.id.button_j);
        button_j.setOnClickListener(this);

        button_k = findViewById(R.id.button_k);
        button_k.setOnClickListener(this);

        button_l = findViewById(R.id.button_l);
        button_l.setOnClickListener(this);

        button_m = findViewById(R.id.button_m);
        button_m.setOnClickListener(this);

        button_n = findViewById(R.id.button_n);
        button_n.setOnClickListener(this);

        button_o = findViewById(R.id.button_o);
        button_o.setOnClickListener(this);

        button_p = findViewById(R.id.button_p);
        button_p.setOnClickListener(this);

        button_q = findViewById(R.id.button_q);
        button_q.setOnClickListener(this);

        button_r = findViewById(R.id.button_r);
        button_r.setOnClickListener(this);

        button_s = findViewById(R.id.button_s);
        button_s.setOnClickListener(this);

        button_t = findViewById(R.id.button_t);
        button_t.setOnClickListener(this);

        button_u = findViewById(R.id.button_u);
        button_u.setOnClickListener(this);

        button_v = findViewById(R.id.button_v);
        button_v.setOnClickListener(this);

        button_x = findViewById(R.id.button_x);
        button_x.setOnClickListener(this);

        button_y = findViewById(R.id.button_y);
        button_y.setOnClickListener(this);

        button_z = findViewById(R.id.button_z);
        button_z.setOnClickListener(this);

        button_æ = findViewById(R.id.button_æ);
        button_æ.setOnClickListener(this);

        button_ø = findViewById(R.id.button_ø);
        button_ø.setOnClickListener(this);

        button_å = findViewById(R.id.button_å);
        button_å.setOnClickListener(this);

        button_reset = findViewById(R.id.button_nulstil);
        button_reset.setOnClickListener(this);

        button_back = findViewById(R.id.button_tilbage);
        button_back.setOnClickListener(this);

    }
}
