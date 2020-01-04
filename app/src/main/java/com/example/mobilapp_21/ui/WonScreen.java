package com.example.mobilapp_21.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.mobilapp_21.R;
import com.example.mobilapp_21.logik.Galgelogik;
import com.github.jinatonic.confetti.CommonConfetti;


import java.util.ArrayList;


public class WonScreen extends AppCompatActivity implements View.OnClickListener {
    private Button button_reset, button_menu;
    private String playerName;
    private int highScore, stop = 0;
    private ArrayList<String> possibleWords = new ArrayList<>();
    private Galgelogik logic;
    private ViewGroup container;
    private Handler mhandler = new Handler();

    @SuppressLint({"SetTextI18n", "ResourceType"})
    @Override
    protected void onCreate(Bundle savedInstanceState) throws NullPointerException {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_won_screen);


        //Byggelse til galgeleg 3.
            container = findViewById(R.id.container);

            won.run();
        logic = Galgelogik.getInstance();

        Bundle lastIntent = getIntent().getExtras();
        if (lastIntent != null) {
            highScore = lastIntent.getInt("Highscore");
            playerName = lastIntent.getString("PlayerName");
        }

        button_menu = findViewById(R.id.button_vandtMenu);
        button_menu.setOnClickListener(this);

        button_reset = findViewById(R.id.button_nulstilVandt);
        button_reset.setOnClickListener(this);

        TextView textView_vundet = findViewById(R.id.vundet);
        textView_vundet.setText(playerName + " du har vundet!");

        TextView textView_tekst = findViewById(R.id.textView_vandt);
        textView_tekst.setText("Ordet var " + logic.getOrdet() +
                "\nDu havde " + logic.getAntalForkerteBogstaver()+ " forkerte\n" +
                "Din Score er: " + highScore);
    }

    @Override
    public void onClick(View v) {
        stop = 1;
        int nulstil = 1;
        if (v == button_menu){
            Intent intent = new Intent(this, Choose_game.class );

            intent.putExtra("PlayerName", playerName);
            finish();
            startActivity(intent);
        }

        if (v == button_reset){
            Intent intent = new Intent(this, GalgeGame.class);
            possibleWords.addAll(logic.getMuligeOrd());
            intent.putExtra("reset", nulstil);
            intent.putExtra("PlayerName", playerName);
            finish();
            logic.nulstil();
            startActivity(intent);
        }
    }
    Runnable won = new Runnable() {

        @Override
        public void run() {
            CommonConfetti.explosion(container,1,1,new int[]{Color.BLUE}).infinite();
            CommonConfetti.explosion(container,container.getWidth(),1,new int[]{Color.BLUE}).infinite();
            if (stop == 1){
                mhandler.removeCallbacks(won);
            } else mhandler.postDelayed(won,1000);
        }
    };

}
