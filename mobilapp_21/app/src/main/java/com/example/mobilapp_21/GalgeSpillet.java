package com.example.mobilapp_21;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class GalgeSpillet extends AppCompatActivity {
    Galgelogik logik = new Galgelogik();
    String sværhedsgrad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gage_spillet);


    }
}
