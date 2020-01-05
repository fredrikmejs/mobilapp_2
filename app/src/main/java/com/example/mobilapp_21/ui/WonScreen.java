package com.example.mobilapp_21.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobilapp_21.R;
import com.example.mobilapp_21.logik.Galgelogik;
import com.example.mobilapp_21.logik.LoadData;
import com.github.jinatonic.confetti.CommonConfetti;


import java.util.ArrayList;


public class WonScreen extends AppCompatActivity implements View.OnClickListener {
    private Button button_reset, button_menu;
    private String playerName;
    private int highScore, stop = 0, mistakes;
    private ArrayList<String> possibleWords = new ArrayList<>();
    private Galgelogik logic;
    private LoadData data;
    private ViewGroup container;
    private Handler mhandler = new Handler();
    private MediaPlayer mediaPlayer;
    private ImageView winPic;


    @SuppressLint({"SetTextI18n", "ResourceType"})
    @Override
    protected void onCreate(Bundle savedInstanceState) throws NullPointerException {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_won_screen);


            container = findViewById(R.id.container);
            won.run();

        logic = Galgelogik.getInstance();
        data = LoadData.getInstance();

        Bundle lastIntent = getIntent().getExtras();
        if (lastIntent != null) {
            highScore = lastIntent.getInt("Highscore");
            mistakes = lastIntent.getInt("mistakes");
        }

        playerName = data.getName();

        winPic = findViewById(R.id.image_won);

        graphic(mistakes);

        button_menu = findViewById(R.id.button_vandtMenu);
        button_menu.setOnClickListener(this);

        button_reset = findViewById(R.id.button_nulstilVandt);
        button_reset.setOnClickListener(this);

        TextView textView_won = findViewById(R.id.vundet);
        textView_won.setText(playerName + " du har vundet!");

        TextView textView_text = findViewById(R.id.textView_vandt);
        textView_text.setText("Ordet var " + logic.getOrdet() +
                "\nDu havde " + logic.getAntalForkerteBogstaver()+ " forkerte\n" +
                "Din Score er: " + highScore);

        //Sound taken from http://soundbible.com/478-Cheering-3.html
        mediaPlayer = MediaPlayer.create(this, R.raw.win);
        mediaPlayer.start();
    }

    @Override
    public void onClick(View v) {
        stop = 5;
        int reset = 1;
        if (v == button_menu){
            Intent intent = new Intent(this, Choose_game.class );
            finish();
            startActivity(intent);
        }

        if (v == button_reset){
            Intent intent = new Intent(this, GalgeGame.class);
            possibleWords.addAll(logic.getMuligeOrd());
            intent.putExtra("reset", reset);
            finish();
            logic.nulstil();
            startActivity(intent);
        }
    }
    Runnable won = new Runnable() {


        /**
         * Confetti is taken from
         * https://android-arsenal.com/details/1/4299
         */
        @Override
        public void run() {
            CommonConfetti.explosion(container,1,1,new int[]{Color.BLUE}).infinite();
            CommonConfetti.explosion(container,container.getWidth(),1,new int[]{Color.BLUE}).infinite();
            CommonConfetti.explosion(container,1,container.getHeight(),new int[]{Color.BLUE}).infinite();
            CommonConfetti.explosion(container,container.getWidth(),container.getHeight(),new int[]{Color.BLUE}).infinite();
            if (stop == 5){
                mhandler.removeCallbacks(won);
            } else {
                stop++;
                mhandler.postDelayed(won,3000);
            }
        }
    };


    private void graphic(int mistakes){
        switch (mistakes){
            case 0:
                winPic.setImageResource(R.drawable.galge);
                break;
            case 1:
                winPic.setImageResource(R.drawable.forkert1);
                break;
            case 2:
                winPic.setImageResource(R.drawable.forkert2);
                break;
            case 3:
                winPic.setImageResource(R.drawable.forkert3);
                break;
            case 4:
                winPic.setImageResource(R.drawable.forkert4);
                break;
            case 5:
                winPic.setImageResource(R.drawable.forkert5);
                break;
            case 6:
                winPic.setImageResource(R.drawable.forkert6);
                break;
        }
    }

}
