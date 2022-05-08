package com.equipoDinamita.covidAmigo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class forgot_password extends AppCompatActivity {

    Button buttonReset, buttonBack;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        buttonReset = findViewById(R.id.reset);
        buttonBack = findViewById(R.id.regresar);

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Aquí va el código para resetear la contraseña

            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sigV();

            }
        });

    }
    public void sigV(){

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

    }
}