package com.equipoDinamita.covidAmigo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioButton;

public class mis_eventos extends AppCompatActivity {

    RadioButton r1,r2;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_eventos);

        r1 =  findViewById(R.id.r1);
        r2 =  findViewById(R.id.r2);

        if (r1.isChecked()){

            //  Aquí vas a meter la consulta para los eventos próximos que asistirá el usuario

        }else{
            if(r2.isChecked()){

                //Aquí vas a meter la consulta para los eventos que organiza el usuario

            }
        }

    }
}