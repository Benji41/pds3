package com.equipoDinamita.covidAmigo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    public void misEventos(View view){
        Intent i = new Intent(this, mis_eventos.class);
        startActivity(i);
    }
    public void menu(View view){
        Intent i = new Intent(this, mis_eventos.class);
        startActivity(i);
    }

    public void logout(View view){

        Intent sig = new Intent(this, MainActivity.class);
        startActivity(sig);

    }
}