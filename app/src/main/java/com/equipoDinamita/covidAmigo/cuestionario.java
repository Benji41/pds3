package com.equipoDinamita.covidAmigo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class cuestionario extends AppCompatActivity {

    Button button;
    RadioButton s1,s2,s3,s4,n1,n2,n3,n4;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuestionario);


        s1 = (RadioButton) findViewById(R.id.s1);
        s2 = (RadioButton) findViewById(R.id.s2);
        s3 = (RadioButton) findViewById(R.id.s3);
        s4 = (RadioButton) findViewById(R.id.s4);
        n1 = (RadioButton) findViewById(R.id.n1);
        n2 = (RadioButton) findViewById(R.id.n2);
        n3 = (RadioButton) findViewById(R.id.n3);
        n4 = (RadioButton) findViewById(R.id.n4);


        
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean x=false;
                if(s1.isChecked()){
                    x=true;

                }else{
                    if(s2.isChecked()){
                        x=true;
                    }else{
                        if(s3.isChecked()){
                            x=true;
                        }else{
                            if(s4.isChecked()){
                                x=true;
                            }
                        }
                    }
                }
                if(x){
                    AlertDialog.Builder d = new AlertDialog.Builder(cuestionario.this);
                    d.setMessage("No es candidato").setCancelable(false).setNegativeButton("Volver", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            menu();
                        }
                    });
                    AlertDialog titulo = d.create();
                    titulo.setTitle("Información");
                    titulo.show();
                }else{
                    AlertDialog.Builder d = new AlertDialog.Builder(cuestionario.this);
                    d.setMessage("Es candidato").setCancelable(false).setPositiveButton("Generar QR", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            qr();
                        }
                    });
                    AlertDialog titulo = d.create();
                    titulo.setTitle("Información");
                    titulo.show();
                }

            }
        });
    }

    public void menu(){
        Intent i = new Intent(this, menu.class);
        startActivity(i);
    }
    public void qr(){
        Intent i = new Intent(this, qr.class);
        startActivity(i);
    }
}