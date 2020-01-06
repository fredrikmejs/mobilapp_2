package com.example.mobilapp_21.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
        textView_secretWord.setText("Gæt ordet" + logic.getSynligtOrd());
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
            updateText();
            graphic();
            button_a.setVisibility(View.INVISIBLE);
        }else if (v == button_b){
            logic.gætBogstav("b");
            updateText();
            graphic();
            button_b.setVisibility(View.INVISIBLE);
        }else if (v == button_c){
            logic.gætBogstav("c");
            updateText();
            graphic();
            button_c.setVisibility(View.INVISIBLE);
        }else if (v == button_d){
            logic.gætBogstav("d");
            updateText();
            graphic();
            button_d.setVisibility(View.INVISIBLE);
        }else if (v == button_e){
            logic.gætBogstav("e");
            updateText();
            graphic();
            button_e.setVisibility(View.INVISIBLE);
        }else if (v == button_f){
            logic.gætBogstav("f");
            updateText();
            graphic();
            button_f.setVisibility(View.INVISIBLE);
        }else if (v == button_g){
            logic.gætBogstav("g");
            updateText();
            graphic();
            button_g.setVisibility(View.INVISIBLE);
        }else if (v == button_h){
            logic.gætBogstav("h");
            updateText();
            graphic();
            button_h.setVisibility(View.INVISIBLE);
        }else if (v == button_i){
            logic.gætBogstav("i");
            updateText();
            graphic();
            button_i.setVisibility(View.INVISIBLE);
        }else if (v == button_j){
            logic.gætBogstav("j");
            updateText();
            graphic();
            button_j.setVisibility(View.INVISIBLE);
        }else if (v == button_k){
            logic.gætBogstav("k");
            updateText();
            graphic();
            button_k.setVisibility(View.INVISIBLE);
        }else if (v == button_l){
            logic.gætBogstav("l");
            updateText();
            graphic();
            button_l.setVisibility(View.INVISIBLE);
        }else if (v == button_m){
            logic.gætBogstav("m");
            updateText();
            graphic();
            button_m.setVisibility(View.INVISIBLE);
        }else if (v == button_n){
            logic.gætBogstav("n");
            updateText();
            graphic();
            button_n.setVisibility(View.INVISIBLE);
        }else if (v == button_o){
            logic.gætBogstav("o");
            updateText();
            graphic();
            button_o.setVisibility(View.INVISIBLE);
        }else if (v == button_p){
            logic.gætBogstav("p");
            updateText();
            graphic();
            button_p.setVisibility(View.INVISIBLE);
        }else if (v == button_q){
            logic.gætBogstav("q");
            updateText();
            graphic();
            button_q.setVisibility(View.INVISIBLE);
        }else if (v == button_r){
            logic.gætBogstav("r");
            updateText();
            graphic();
            button_r.setVisibility(View.INVISIBLE);
        }else if (v == button_s){
            logic.gætBogstav("s");
            updateText();
            graphic();
            button_s.setVisibility(View.INVISIBLE);
        }else if (v == button_t){
            logic.gætBogstav("t");
            updateText();
            graphic();
            button_t.setVisibility(View.INVISIBLE);
        }else if (v == button_u){
            logic.gætBogstav("u");
            updateText();
            graphic();
            button_u.setVisibility(View.INVISIBLE);
        }else if (v == button_v){
            logic.gætBogstav("v");
            updateText();
            graphic();
            button_v.setVisibility(View.INVISIBLE);
        }else if (v == button_x){
            logic.gætBogstav("x");
            updateText();
            graphic();
            button_x.setVisibility(View.INVISIBLE);
        }else if (v == button_y){
            logic.gætBogstav("y");
            updateText();
            graphic();
            button_y.setVisibility(View.INVISIBLE);
        }else if (v == button_z){
            logic.gætBogstav("z");
            updateText();
            graphic();
            button_z.setVisibility(View.INVISIBLE);
        }else if (v == button_æ){
            logic.gætBogstav("æ");
            updateText();
            graphic();
            button_æ.setVisibility(View.INVISIBLE);
        }else if (v == button_ø){
            logic.gætBogstav("ø");
            updateText();
            graphic();
            button_ø.setVisibility(View.INVISIBLE);
        }else if (v == button_å){
            logic.gætBogstav("å");
            updateText();
            graphic();
            button_å.setVisibility(View.INVISIBLE);
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
