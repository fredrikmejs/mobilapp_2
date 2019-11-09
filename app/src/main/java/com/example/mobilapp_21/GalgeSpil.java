package com.example.mobilapp_21;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class GalgeSpil extends AppCompatActivity implements View.OnClickListener {
    Galgelogik logik = new Galgelogik();
    private Button button_gæt, button_tilbage, button_nulstil;
    private TextView textView_hemmeligtOrd;
    private EditText editText_gæt;
    private String sværhedsgrad;
    private ImageView imageView_spil;
    private Handler mhandler = new Handler();
    private int forkerte, nytSpil = 0, tilbage = 1, spilletype;

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

            spilletype = lastIntent.getInt("GameType");
        }

        if (spilletype == 0){
            hentDr.start();
            try {
                //Så tråden når at finde et ord inden jeg forsætter
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (spilletype == 1){
            hentRegneArk.start();
            try {
                //Så tråden når at finde et ord inden jeg forsætter
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
            Intent intent = getIntent();
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
        textView_hemmeligtOrd.setText("Gæt ordet " + logik.getSynligtOrd() +
                "\nAntal forkerte bogstaver: " + logik.getAntalForkerteBogstaver() +
                "\nBrugt følgende bogstaver: " + logik.getBrugteBogstaver()
        );

        if (logik.erSpilletVundet()) {
            textView_hemmeligtOrd.setText("\nDu har vundet" + "\n Ordet var " + logik.getOrdet() +
                    "\nDu brugte " + logik.getBrugteBogstaver().size() + " bogstaver");
        }
        if (logik.erSpilletTabt()) {
            textView_hemmeligtOrd.setText("Du har tabt, ordet var : " + logik.getOrdet());
            editText_gæt.setVisibility(View.INVISIBLE);
            button_gæt.setVisibility(View.INVISIBLE);
        }
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
        }
    };






}
