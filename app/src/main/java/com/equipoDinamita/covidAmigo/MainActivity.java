package com.equipoDinamita.covidAmigo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    Button button;
    TextView tv,tv2;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.signIn);

        tv = findViewById(R.id.forgotPassword);
        tv2 = findViewById(R.id.register);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sigV();

            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sigV2();

            }
        });

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sigV3();

            }
        });


        if(getIntent().getBooleanExtra("EXIT",false)){
            finish();
        }

    }


    public void sigV(){
        Intent sig = new Intent(this, menu.class);
        startActivity(sig);
    }
    public void sigV2(){
        Intent sig = new Intent(this, forgot_password.class);
        startActivity(sig);
    }
    public void sigV3(){
        Intent sig = new Intent(this, register_user.class);
        startActivity(sig);
    }
}