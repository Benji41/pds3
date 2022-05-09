package com.equipoDinamita.covidAmigo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class register_user extends AppCompatActivity {

    Button buttonBack, buttonRegister;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        buttonBack = findViewById(R.id.regresar);
        buttonRegister = findViewById(R.id.registerUser);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Accionar registro

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