package com.equipoDinamita.covidAmigo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class bloq extends AppCompatActivity {

    Button b_regresar;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloq);

        b_regresar = findViewById(R.id.regre);

        b_regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                regresar();

            }
        });

    }

    public void regresar(){

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

    }
}