package com.equipoDinamita.covidAmigo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.equipoDinamita.Interface.API;
import com.equipoDinamita.Model.User;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class profile extends AppCompatActivity {
    String email;
    User user;
    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl("https://covid-amigo-pds3.herokuapp.com/").addConverterFactory(GsonConverterFactory.create());
    public static Retrofit retrofit = builder.build();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        if (getIntent().hasExtra("DATA_EMAIL_KEY")){
            email =getIntent().getStringExtra("DATA_EMAIL_KEY");
            getUserAPI();
        }
    }


    public void manual(View view){

        //Intent i = new Intent(this, )

    }
    public void misEventos(View view){
        Intent i = new Intent(this, mis_eventos.class);
        startActivity(i);
    }
    public void menu(View view){
        Intent i = new Intent(this, menu.class);
        startActivity(i);
    }
    private void getUserAPI() {
        API GetProfilefAPI = retrofit.create(API.class);
        retrofit2.Call<User> Call = GetProfilefAPI.getProfile(email);
        Call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(retrofit2.Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    user = response.body();
                    Toast.makeText(profile.this, response.body().toString(), Toast.LENGTH_LONG).show();
                }else{
                    if(!response.errorBody().toString().isEmpty()){
                        Toast.makeText(profile.this, "Error de Back-End", Toast.LENGTH_LONG).show();
                    }
                }
            }
            @Override
            public void onFailure(retrofit2.Call<User> call, Throwable t) {
                System.out.println(t);
                Toast.makeText(profile.this, "Error de conexion", Toast.LENGTH_LONG).show();
            }
        });
    }






    public void logout(View view){
        Intent sig = new Intent(this, MainActivity.class);
        startActivity(sig);
    }
}