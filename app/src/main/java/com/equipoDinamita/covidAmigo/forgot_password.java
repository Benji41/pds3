package com.equipoDinamita.covidAmigo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgot_password extends AppCompatActivity {

    Button buttonReset, buttonBack;
    EditText mTextEmail;
    FirebaseAuth auth;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        mTextEmail = (EditText) findViewById(R.id.email);
        buttonReset = findViewById(R.id.reset);
        buttonBack = findViewById(R.id.regresar);
        auth = FirebaseAuth.getInstance();
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
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
        Intent sig = new Intent(this, MainActivity.class);
        startActivity(sig);
    }

    private void resetPassword(){
        String email = mTextEmail.getText().toString().trim();

        if(email.isEmpty()){
            mTextEmail.setError("Email es requerido!");
            mTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mTextEmail.setError("Porfavor ingrese un email valido!");
            return;
        }
        //progressBar.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){
                    Toast.makeText(forgot_password.this, "Checa tu correo para resetear Password", Toast.LENGTH_LONG).show();
                    //progressBar.setVisibility(View.GONE);
                    sigV();

                }else{
                    Toast.makeText(forgot_password.this, "Intenta de nuevo! Algo salio mal", Toast.LENGTH_LONG).show();
                    //progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
}