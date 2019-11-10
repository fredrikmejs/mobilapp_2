package com.example.mobilapp_21.logik;

import android.annotation.SuppressLint;

import java.util.ArrayList;


public class Score {

    ArrayList<Score> highscoreListe = new ArrayList<>();


    public String name;
    public int score;


    public Score(String name, int score) {
        this.name = name;
        this.score = score;
    }



    @SuppressLint("DefaultLocale")
    @Override
    public String toString(){

        return String.format("Name: %s, Score: %d", name, score);
    }


}

