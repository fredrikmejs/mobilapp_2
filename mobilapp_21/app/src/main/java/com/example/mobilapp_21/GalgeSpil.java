package com.example.mobilapp_21;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
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
    private int forkerte, nytSpil = 0, tilbage = 1;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galge_spil);


        //Bruger sværhedsgraden fra sidste aktivitet
        Bundle lastIntent = getIntent().getExtras();
        if (lastIntent != null) {
            sværhedsgrad = lastIntent.getString("sværhedsgrad");
        }




        //Tjekker om den skal nulstille eller ej
        if (nytSpil == 0){
            try {
                //Henter ord fra arket
                logik.hentOrdFraRegneark(sværhedsgrad);
            } catch (Exception e) {
                e.printStackTrace();
            }
            logik.nulstil();
            nytSpil++;
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
            //Går jeg man kan gå tilbage, hvis man vil ændre f.eks sværhedsgraden. Men hvis man fortryder kan man forsætte sit nuværende spil
            Intent intent = new Intent(GalgeSpil.this, Difficulty.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY);
            intent.putExtra("tilbage_tal", tilbage);
            startActivity(intent);
        }

        //Nulstiller spillet og finder et nyt ord
        if (v == button_nulstil){
            logik.nulstil();
            textView_hemmeligtOrd.setText("Gæt ordet " + logik.getSynligtOrd());
            grafik();
            editText_gæt.setVisibility(View.VISIBLE);
            button_gæt.setVisibility(View.VISIBLE);

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
                    "\nDu brugte " + logik.getBrugteBogstaver().size() + "bogstaver");
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
}
