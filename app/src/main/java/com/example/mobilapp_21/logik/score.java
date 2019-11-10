package com.example.mobilapp_21.logik;

import java.util.ArrayList;

public class score {

    ArrayList<score> highscoreListe = new ArrayList<>();


    private String name;
    private int score;

    public score(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public void setHighScoreListe(String navn, int score){
        highscoreListe.add(new score(navn,score));
    }

    public ArrayList getHighscoreListe(){return highscoreListe;}



}

