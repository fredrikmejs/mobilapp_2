package com.example.mobilapp_21.logik;

import android.annotation.SuppressLint;


public class Score {

    private String name;
    int score;

    //Constructor til Score objektet.
    Score(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @SuppressLint("DefaultLocale")
    @Override
    public String toString(){

        return String.format("Spiller: %s fik scoren: %d", name, score);
    }


}

