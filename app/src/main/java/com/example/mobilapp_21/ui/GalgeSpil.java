package com.example.mobilapp_21.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobilapp_21.R;
import com.example.mobilapp_21.logik.Galgelogik;

import java.util.ArrayList;

public class GalgeSpil extends AppCompatActivity implements View.OnClickListener {
    Galgelogik logik = new Galgelogik();
    private Button button_gæt, button_tilbage, button_nulstil;
    private TextView textView_hemmeligtOrd;
    private EditText editText_gæt;
    private String sværhedsgrad;
    private ImageView imageView_spil;
    private int forkerte, spilletype, nulstil = 0, score;
    private boolean forsæt = true;
    private ArrayList<String> muligeOrd = new ArrayList<>();
    private ArrayList<com.example.mobilapp_21.logik.score> highscoreListe = new ArrayList<>();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galge_spil);


        //Bruger sværhedsgraden fra sidste aktivitet
        Bundle lastIntent = getIntent().getExtras();
        if (lastIntent != null) {
            if (lastIntent.getString("sværhedsgrad") != null)
                sværhedsgrad = lastIntent.getString("sværhedsgrad");

            if (lastIntent.getInt("nulstil") != 0) {
                nulstil = lastIntent.getInt("nulstil");
                muligeOrd = lastIntent.getStringArrayList("muligeOrd");
            }

            spilletype = lastIntent.getInt("GameType");
        }


       if (nulstil == 0) {
            if (spilletype == 0) {
                hentDr.start();
            } else if (spilletype == 1) {
                hentRegneArk.start();
            }
        } else {
            logik.setMuligeOrd(muligeOrd)   ;
            logik.nulstil();
            forsæt = false;
        }

        button_gæt = findViewById(R.id.button_gæt);
        button_gæt.setOnClickListener(this);

        button_nulstil = findViewById(R.id.button_nulstil);
        button_nulstil.setOnClickListener(this);

        button_tilbage = findViewById(R.id.button_tilbage);
        button_tilbage.setOnClickListener(this);

        editText_gæt = findViewById(R.id.editText_gæt);

        imageView_spil = findViewById(R.id.imageView_spil);
        grafik();

        textView_hemmeligtOrd = findViewById(R.id.textView_hemmeligtOrd);
        //Venter til tråden er færdig
        while (forsæt){}

        textView_hemmeligtOrd.setText("Gæt ordet" + logik.getSynligtOrd());
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {

        if (v == button_gæt){
            //Får det indtastede svar
            String svar = editText_gæt.getText().toString();

            //sikre mig det er et gyldigt svar
            if (svar.length() == 1){
                logik.gætBogstav(svar);
                editText_gæt.setText("");
                opdaterTekst();
                grafik();
                logik.getOrdet();
            }else {
                editText_gæt.setError("Ugyldigt gæt");
                return;
            }
        }
        if (v == button_tilbage){
            //Går jeg man kan gå tilbage til menu'en
            Intent intent = new Intent(GalgeSpil.this, Choose_game.class);
            finish();
            startActivity(intent);
        }

        //Nulstiller spillet og finder et nyt ord
        if (v == button_nulstil){
            nulstil = 1;
            muligeOrd.addAll(logik.getMuligeOrd());
            Intent intent = getIntent();
            intent.putStringArrayListExtra("muligeOrd",muligeOrd);
            intent.putExtra("nulstil",nulstil);
            intent.putExtra("sværhedsgrad",sværhedsgrad);
            intent.putExtra("GameType",spilletype);
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

            Intent intent = new Intent(this, WonScreen.class);
            intent.putExtra("ordet",logik.getOrdet());
            intent.putExtra("forkerte",logik.getBrugteBogstaver());
            intent.putExtra("antalForkerte",logik.getAntalForkerteBogstaver());
            intent.putExtra("Highscore",beregnScore());
            finish();
            startActivity(intent);
        }
        if (logik.erSpilletTabt()) {
            Intent intent = new Intent(this, LostScreen.class);
            intent.putExtra("ordet",logik.getOrdet());
            intent.putExtra("forkerte",logik.getBrugteBogstaver());
            intent.putExtra("antalForkerte",logik.getAntalForkerteBogstaver());
            intent.putExtra("Highscore",beregnScore());
            finish();
            startActivity(intent);

        }
    }

    public int beregnScore() {
        score = 1000;
        boolean erVundet = logik.erSpilletVundet(), erTabt = logik.erSpilletTabt();
        int forkerte = logik.getAntalForkerteBogstaver(), antalkorrekte = logik.getAntalKorrekte();

        if (nulstil == 0 || (nulstil == 1 && sværhedsgrad.equals("3")) && erVundet) {
            score = 1000 - forkerte * 50;
            return score;
        } else if (nulstil == 1 && sværhedsgrad.equals("2") && erVundet) {

            score = 1000 - 200 - (forkerte * 50);
            return score;
        } else if (nulstil == 1 && sværhedsgrad.equals("1") && erVundet) {
            score = 1000 - 400 - (forkerte * 50);
            return score;
        } else if (nulstil == 0 || (nulstil == 1 && sværhedsgrad.equals("3")) && erTabt) {
            score = antalkorrekte*85;
            return score;
        } else if (nulstil == 1 && sværhedsgrad.equals("2") && erTabt) {
            score = antalkorrekte*50;
            return score;
        } else if (nulstil == 1 && sværhedsgrad.equals("1") && erTabt) {
            score = antalkorrekte*40;
            return score;
        }
        return score;
    }


    /**
     * Skifter billedet afhængigt af hvor mange forkerte der er.
     */
    private void grafik(){
        forkerte = logik.getAntalForkerteBogstaver();
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

    Thread hentRegneArk = new Thread(){

        public void run(){
            try {
                logik.hentOrdFraRegneark(sværhedsgrad);
            } catch (Exception e) {
                e.printStackTrace();
            }
            logik.nulstil();
            forsæt = false;
        }
    };


    Thread hentDr = new Thread(){

        public void run(){
            try {
                logik.hentOrdFraDr();
            } catch (Exception e) {
                e.printStackTrace();
            }
            logik.nulstil();
            forsæt = false;
        }
    };






}
