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
import com.google.gson.Gson;

import java.util.ArrayList;

public class GalgeGame extends AppCompatActivity implements View.OnClickListener {
    Galgelogik logic;
    private Button button_guess, button_back, button_reset;
    private TextView textView_secretWord;
    private EditText editText_guess;
    private String dfficulty, playName;
    private ImageView imageView_game;
    private int gameType;
    private int reset = 0;
    private ArrayList<String> possibleWords = new ArrayList<>();


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galge_spil);
        logic = Galgelogik.getInstance();


        //Uses the difficulty from Difficulty
        Bundle lastIntent = getIntent().getExtras();
        if (lastIntent != null) {
            if (lastIntent.getString("difficulty") != null)
                dfficulty = lastIntent.getString("difficulty");
            if (lastIntent.getInt("reset") != 0)
                reset = lastIntent.getInt("nulstil");
            if (lastIntent.getString("PlayerName") != null)
                playName = lastIntent.getString("PlayerName");
            gameType = lastIntent.getInt("GameType");
        }

        button_guess = findViewById(R.id.button_gæt);
        button_guess.setOnClickListener(this);

        button_reset = findViewById(R.id.button_nulstil);
        button_reset.setOnClickListener(this);

        button_back = findViewById(R.id.button_tilbage);
        button_back.setOnClickListener(this);

        editText_guess = findViewById(R.id.editText_gæt);

        imageView_game = findViewById(R.id.imageView_spil);
        graphic();


        textView_secretWord = findViewById(R.id.textView_hemmeligtOrd);
        textView_secretWord.setText("Gæt ordet" + logic.getSynligtOrd());
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {

        if (v == button_guess){
            String svar = editText_guess.getText().toString();

            //Checks if it's a valid answer
            if (svar.length() == 1){
                logic.gætBogstav(svar);
                editText_guess.setText("");
                opdaterTekst();
                graphic();
            }else {
                editText_guess.setText("");
                editText_guess.setError("Ugyldigt gæt");
                return;
            }
        }
        if (v == button_back){
            //Går jeg man kan gå tilbage til menu'en
            Intent intent = new Intent(GalgeGame.this, Choose_game.class);
            intent.putExtra("PlayerName", playName);
            finish();
            startActivity(intent);
        }

        //resets the game and finds a new word
        if (v == button_reset){
            reset = 1;
            logic.nulstil();
            possibleWords.addAll(logic.getMuligeOrd());
            Intent intent = getIntent();
            intent.putExtra("reset",reset);
            intent.putExtra("difficulty",dfficulty);
            intent.putExtra("GameType", gameType);
            intent.putExtra("PlayerName", playName);
            finish();
            startActivity(intent);
        }
    }

    /**
     * Tekst, når der bliver gættet.
     */
    @SuppressLint("SetTextI18n")
    private void opdaterTekst() {
        textView_secretWord.setText("Gæt ordet   " + logic.getSynligtOrd() +
                "\nAntal forkerte bogstaver: " + logic.getAntalForkerteBogstaver() +
                "\nBrugt følgende bogstaver: " + logic.getBrugteBogstaver()
        );

        if (logic.erSpilletVundet()) {
            int highscore = beregnScore();
            logic.setHighScoreListe(playName, highscore);
            saveData();
            Intent intent = new Intent(this, WonScreen.class);
            intent.putExtra("Highscore",highscore);
            intent.putExtra("PlayerName", playName);
            finish();
            startActivity(intent);
        }
        if (logic.erSpilletTabt()) {
            int highscore = beregnScore();
            logic.setHighScoreListe(playName, highscore);
            saveData();
            Intent intent = new Intent(this, LostScreen.class);
            intent.putExtra("Highscore",highscore);
            intent.putExtra("PlayerName", playName);
            finish();
            startActivity(intent);
        }
    }

    public int beregnScore() {
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
}
