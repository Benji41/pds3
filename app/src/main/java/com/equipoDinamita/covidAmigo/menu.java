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

    public void ventanaInfo(View view){

        Intent i = new Intent(this, acerca_del_equipo.class);
        startActivity(i);

    }
    public void sigVentana(View view){

        Intent sig = new Intent(this, epd.class);
        startActivity(sig);

    }

    public void exit(View view){

        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.putExtra("EXIT", true);
        startActivity(i);
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