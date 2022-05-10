package com.equipoDinamita.covidAmigo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class menu extends AppCompatActivity {
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        if (getIntent().hasExtra("DATA_EMAIL_KEY")){
            email =getIntent().getStringExtra("DATA_EMAIL_KEY");
        }
    }

    public void ventanaInfo(View view){

        Intent sig = new Intent(this, acerca_del_equipo.class);
        sig.putExtra("DATA_EMAIL_KEY",email);
        startActivity(sig);

    }
    public void sigVentana(View view){

        Intent sig = new Intent(this, epd.class);
        sig.putExtra("DATA_EMAIL_KEY",email);
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
        sig.putExtra("DATA_EMAIL_KEY",email);
        startActivity(sig);

    }

    public void ventanaPerfil(View view){
        Intent sig = new Intent(this, profile.class);
        sig.putExtra("DATA_EMAIL_KEY",email);
        startActivity(sig);
    }
}