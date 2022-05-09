package com.equipoDinamita.covidAmigo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.equipoDinamita.Interface.API;
import com.equipoDinamita.Model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class register_user extends AppCompatActivity {

    Button buttonBack, buttonRegister;
    EditText etEmail, etName,etSurname,etAge,etPassword;
    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl("https://covid-amigo-pds3.herokuapp.com/").addConverterFactory(GsonConverterFactory.create());
    public static Retrofit retrofit = builder.build();
    private User user;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        buttonBack =(Button) findViewById(R.id.regresar);
        buttonRegister =(Button) findViewById(R.id.registerUser);
        etEmail =(EditText) findViewById(R.id.email);
        etName = (EditText) findViewById(R.id.nombre);
        etSurname = (EditText) findViewById(R.id.apellido);
        etAge = (EditText) findViewById(R.id.edad);
        etPassword = (EditText) findViewById(R.id.pass);



        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = new User(etName.getText().toString(),etSurname.getText().toString(),etEmail.getText().toString(),"","","",Integer.parseInt(etAge.getText().toString()),0,0);
                registerUserAPI(user.getEmail(),user.getNombre(),user.getApellidos(),user.getEdad(),user.getFoto());
                Toast.makeText(register_user.this, "Usuario registrado!!!", Toast.LENGTH_LONG).show();
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sigV();

            }
        });
    }



    private void registerUserAPI(String email, String name, String lastNames, Integer age, String foto){
        API RegistroAPI = retrofit.create(API.class);
        System.out.println(user.toString());
        Call<User> Call = RegistroAPI.insertUser(email, name, lastNames, age,foto);
        Call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(retrofit2.Call<User> call, Response<User> response) {
                Toast.makeText(register_user.this, "Registro terminado", Toast.LENGTH_LONG).show();
                System.out.println(response.body().toString());
            }
            @Override
            public void onFailure(retrofit2.Call<User> call, Throwable t) {
                Toast.makeText(register_user.this, t.toString(), Toast.LENGTH_LONG).show();

            }
        });

    }

    /*String email= editTextemail.getText().toString().trim();
        String password= editTextpassword.getText().toString().trim();
        String name= editTextname.getText().toString().trim();
        String lastName= editTextlastname.getText().toString().trim();
        String age= editTextage.getText().toString().trim();

        if((name.isEmpty())){
            editTextname.setError("Full name is required!");
            editTextname.requestFocus();
            return;
        }

        if((lastName.isEmpty())){
            editTextlastname.setError("Last name is required!");
            editTextlastname.requestFocus();
            return;
        }

        if(age.isEmpty()){
            editTextage.setError("Age is required!");
            editTextage.requestFocus();
            return;
        }

        if(email.isEmpty()){
            editTextemail.setError("Email is required!");
            editTextemail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextemail.setError("Please provide valid email");
            editTextemail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextpassword.setError("Password is required!");
            editTextpassword.requestFocus();
            return;
        }

        if(password.length() < 6){
            editTextpassword.setError("Min password length should be 6 characters");
            editTextpassword.requestFocus();
            return;
        }*/



    public void sigV(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}