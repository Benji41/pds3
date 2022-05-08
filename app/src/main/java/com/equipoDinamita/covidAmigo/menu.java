package com.equipoDinamita.covidAmigo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void sigVentana(View view){

        Intent sig = new Intent(this, epd.class);
        startActivity(sig);

    }

    public void salir(View view){

        System.exit(0);
    }

    public void misEventos(View view){

        Intent sig = new Intent(this, mis_eventos.class);
        startActivity(sig);

    }

    public void ventanaPerfil(View view){

        Intent sig = new Intent(this, profile.class);
        startActivity(sig);

    }
}