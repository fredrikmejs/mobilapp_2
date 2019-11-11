package com.example.mobilapp_21.logik;

import android.annotation.SuppressLint;


public class Score {

    public String name;
    public int score;


    public Score(String name, int score) {
        this.name = name;
        this.score = score;
    }



    @SuppressLint("DefaultLocale")
    @Override
    public String toString(){

        return String.format("Spiller: %s, Score: %d", name, score);
    }


}

