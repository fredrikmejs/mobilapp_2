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
import java.util.concurrent.CountDownLatch;

public class GalgeSpil extends AppCompatActivity implements View.OnClickListener {
    Galgelogik logik;
    private Button button_guess, button_tilbage, button_nulstil;
    private TextView textView_hemmeligtOrd;
    private EditText editText_guess;
    private String dfficulty, spillerNavn;
    private ImageView imageView_spil;
    private int spilletype;
    private int nulstil = 0;;
    private ArrayList<String> muligeOrd = new ArrayList<>();
    final CountDownLatch latch = new CountDownLatch(1);

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galge_spil);
        logik = Galgelogik.getInstance();


        //Bruger sværhedsgraden fra sidste aktivitet
        Bundle lastIntent = getIntent().getExtras();
        if (lastIntent != null) {
            if (lastIntent.getString("sværhedsgrad") != null)
                dfficulty = lastIntent.getString("sværhedsgrad");
            if (lastIntent.getInt("nulstil") != 0)
                nulstil = lastIntent.getInt("nulstil");
            if (lastIntent.getString("SpillerNavn") != null)
                spillerNavn = lastIntent.getString("SpillerNavn");
            spilletype = lastIntent.getInt("GameType");
        }

       if (nulstil == 0) {
           if (spilletype == 0)
               hentDr.start();
           else if (spilletype == 1)
               hentRegneArk.start();
        } else
           logik.nulstil();

        button_guess = findViewById(R.id.button_gæt);
        button_guess.setOnClickListener(this);

        button_nulstil = findViewById(R.id.button_nulstil);
        button_nulstil.setOnClickListener(this);

        button_tilbage = findViewById(R.id.button_tilbage);
        button_tilbage.setOnClickListener(this);

        editText_guess = findViewById(R.id.editText_gæt);

        imageView_spil = findViewById(R.id.imageView_spil);
        grafik();


        //Venter til tråden er færdig

        if (nulstil == 0) {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        textView_hemmeligtOrd = findViewById(R.id.textView_hemmeligtOrd);
        textView_hemmeligtOrd.setText("Gæt ordet" + logik.getSynligtOrd());
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {

        if (v == button_guess){
            //Får det indtastede svar
            String svar = editText_guess.getText().toString();

            //sikre mig det er et gyldigt svar
            if (svar.length() == 1){
                logik.gætBogstav(svar);
                editText_guess.setText("");
                opdaterTekst();
                grafik();
            }else {
                editText_guess.setText("");
                editText_guess.setError("Ugyldigt gæt");
                return;
            }
        }
        if (v == button_tilbage){
            //Går jeg man kan gå tilbage til menu'en
            Intent intent = new Intent(GalgeSpil.this, Choose_game.class);
            intent.putExtra("SpillerNavn",spillerNavn);
            finish();
            startActivity(intent);
        }

        //Nulstiller spillet og finder et nyt ord
        if (v == button_nulstil){
            nulstil = 1;
            muligeOrd.addAll(logik.getMuligeOrd());
            Intent intent = getIntent();
            intent.putExtra("nulstil",nulstil);
            intent.putExtra("sværhedsgrad",dfficulty);
            intent.putExtra("GameType",spilletype);
            intent.putExtra("SpillerNavn",spillerNavn);
            finish();
            startActivity(intent);
        }
    }

    /**
     * Tekst, når der bliver gættet.
     */
    @SuppressLint("SetTextI18n")
    private void opdaterTekst() {
        textView_hemmeligtOrd.setText("Gæt ordet  " + logik.getSynligtOrd() +
                "\nAntal forkerte bogstaver: " + logik.getAntalForkerteBogstaver() +
                "\nBrugt følgende bogstaver: " + logik.getBrugteBogstaver()
        );

        if (logik.erSpilletVundet()) {
            int highscore = beregnScore();
            logik.setHighScoreListe(spillerNavn, highscore);
            saveData();
            Intent intent = new Intent(this, WonScreen.class);
            intent.putExtra("Highscore",highscore);
            intent.putExtra("SpillerNavn",spillerNavn);
            finish();
            startActivity(intent);
        }
        if (logik.erSpilletTabt()) {
            int highscore = beregnScore();
            logik.setHighScoreListe(spillerNavn, highscore);
            saveData();
            Intent intent = new Intent(this, LostScreen.class);
            intent.putExtra("Highscore",highscore);
            intent.putExtra("SpillerNavn",spillerNavn);
            finish();
            startActivity(intent);
        }
    }

    public int beregnScore() {
        int score = 1000;
        boolean erVundet = logik.erSpilletVundet(), erTabt = logik.erSpilletTabt();
        int forkerte = logik.getAntalForkerteBogstaver();
        int antalkorrekte = logik.getAntalKorrekte();

        if ((spilletype == 0 && erVundet) || (spilletype == 1 && dfficulty.equals("3")) && erVundet) {
            score = 1000 - forkerte * 50;
            return score;
        } else if (spilletype == 1 && dfficulty.equals("2") && erVundet) {
            score = 1000 - 200 - (forkerte * 50);
            return score;
        } else if (spilletype == 1 && dfficulty.equals("1") && erVundet) {
            score = 1000 - 400 - (forkerte * 50);
            return score;
        } else if (spilletype == 0 || (spilletype == 1 && dfficulty.equals("3")) && erTabt) {
            score = antalkorrekte*65;
            return score;
        } else if (spilletype == 1 && dfficulty.equals("2") && erTabt) {
            score = antalkorrekte*45;
            return score;
        } else if (spilletype == 1 && dfficulty.equals("1") && erTabt) {
            score = antalkorrekte*35;
            return score;
        }
        return score;
    }

    /**
     * Gemmer data lokalt i en cache
     */
    void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("Shared",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(logik.getHighscoreListe());
        editor.putString("topscoreListe",json);
        editor.apply();
    }

    /**
     * Skifter billedet afhængigt af hvor mange forkerte der er.
     */
    private void grafik(){
        int forkerte = logik.getAntalForkerteBogstaver();
        switch (forkerte){
            case 0:
                imageView_spil.setImageResource(R.drawable.galge);
                break;
            case 1:
                imageView_spil.setImageResource(R.drawable.forkert1);
                break;
            case 2:
                imageView_spil.setImageResource(R.drawable.forkert2);
                break;
            case 3:
                imageView_spil.setImageResource(R.drawable.forkert3);
                break;
            case 4:
                imageView_spil.setImageResource(R.drawable.forkert4);
                break;
            case 5:
                imageView_spil.setImageResource(R.drawable.forkert5);
                break;
            case 6:
                imageView_spil.setImageResource(R.drawable.forkert6);
                break;
        }
    }

    Thread hentRegneArk = new Thread() {

        public void run() {
            try {
                logik.hentOrdFraRegneark(dfficulty);
                latch.countDown();
            } catch (Exception e) {
                e.printStackTrace();
            }
            logik.nulstil();
        }
    };


    Thread hentDr = new Thread(){

        public void run(){
            try {
                logik.hentOrdFraDr();
                latch.countDown();
            } catch (Exception e) {
                e.printStackTrace();
            }
            logik.nulstil();
        }
    };






}
