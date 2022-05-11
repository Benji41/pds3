package com.equipoDinamita.covidAmigo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class mis_eventos extends AppCompatActivity {

    RadioButton r1,r2;
    Button buttonRegresar,buttonRegistrarEvento;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_eventos);

        r1 =  findViewById(R.id.r1);
        r2 =  findViewById(R.id.r2);

        buttonRegresar = findViewById(R.id.regresar);
        buttonRegistrarEvento = findViewById(R.id.registrarEvento);

        buttonRegistrarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                re();

            }
        });

        if (r1.isChecked()){

            //  Aquí vas a meter la consulta para los eventos próximos que asistirá el usuario

        }else{
            if(r2.isChecked()){

                //Aquí vas a meter la consulta para los eventos que organiza el usuario

            }
        }

        buttonRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sigV();

            }
        });

    }

    public void sigV(){
        Intent i = new Intent(this, menu.class);
        startActivity(i);
    }
    public void re(){
        Intent i = new Intent(this, registrar_evento.class);
        startActivity(i);
    }
}