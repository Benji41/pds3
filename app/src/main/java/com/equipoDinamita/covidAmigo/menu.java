package com.equipoDinamita.covidAmigo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.equipoDinamita.Interface.API;
import com.equipoDinamita.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class menu extends AppCompatActivity {
    Button mButtonAlertaCovid;
    String email;
    int salud;
    User user;
    List<User> userListsPrevious;
    List<User> userListsUpcoming;
    AlertDialog.Builder builderDialog;
    DialogInterface.OnClickListener dialogClickListener;
    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl("https://covid-amigo-pds3.herokuapp.com/").addConverterFactory(GsonConverterFactory.create());
    public static Retrofit retrofit = builder.build();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        mButtonAlertaCovid = findViewById(R.id.bac);
        if (getIntent().hasExtra("DATA_EMAIL_KEY")){
            email =getIntent().getStringExtra("DATA_EMAIL_KEY");
            if(getIntent().hasExtra("DATA_HEALTH_KEY")){
                salud = getIntent().getIntExtra("DATA_HEALTH_KEY", -1);
                if(salud !=0){
                    Toast.makeText(this, "estoy enfermo",Toast.LENGTH_LONG).show();
                    //abrir ventana de que esta enfermo
                }
            }
        }

        mButtonAlertaCovid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builderDialog = new AlertDialog.Builder(menu.this);
                builderDialog.setMessage("¿Estas seguro de que estas contagiado?").setPositiveButton("Si", dialogClickListener).setNegativeButton("No", dialogClickListener).show();
            }
        });

        dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        blockUser();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };

    }
    private void blockUser() {
        API block= retrofit.create(API.class);
        retrofit2.Call<User> Call = block.blockUser(email);
        Call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(retrofit2.Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    user = response.body();
                    if(user.getUs_health()==1){
                        alert(block);
                    }
                }else{
                    if(!response.errorBody().toString().isEmpty()){
                        Toast.makeText(menu.this, "Error de Back-End", Toast.LENGTH_LONG).show();
                    }
                }
            }
            @Override
            public void onFailure(retrofit2.Call<User> call, Throwable t) {
                Toast.makeText(menu.this, "Error de conexion", Toast.LENGTH_LONG).show();
            }
        });
    }
    private void alert(API alert){
        Call<List<User>> Call = alert.getAttendesEvents(email);
        Call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(retrofit2.Call<List<User>> call, Response<List<User>> response) {
                if(response.isSuccessful()){
                    userListsPrevious = response.body();
                }else{
                    if(!response.errorBody().toString().isEmpty()){
                        Toast.makeText(menu.this, "Error de Back-End", Toast.LENGTH_LONG).show();
                    }
                }
            }
            @Override
            public void onFailure(retrofit2.Call<List<User>> call, Throwable t) {
                Toast.makeText(menu.this, "Error de conexion", Toast.LENGTH_LONG).show();
            }
        });

        Call<List<User>> Call2 = alert.getAttendesUpcomingEvents(email);
        Call2.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(retrofit2.Call<List<User>> call, Response<List<User>> response) {
                if(response.isSuccessful()){
                    userListsUpcoming = response.body();
                }else{
                    if(!response.errorBody().toString().isEmpty()){
                        Toast.makeText(menu.this, "Error de Back-End", Toast.LENGTH_LONG).show();
                    }
                }

            }
            @Override
            public void onFailure(retrofit2.Call<List<User>> call, Throwable t) {
                Toast.makeText(menu.this, "Error de conexion", Toast.LENGTH_LONG).show();
            }
        });
        System.out.println(userListsPrevious+" "+userListsUpcoming);
    }
    public void ventanaInfo(View view){

        Intent sig = new Intent(this, acerca_del_equipo.class);
        sig.putExtra("DATA_EMAIL_KEY",email);
        startActivity(sig);

    }
    public void sigVentana(View view){

        Intent sig = new Intent(this, epd.class);
        sig.putExtra("DATA_EMAIL_KEY",email);
        startActivity(sig);

    }
    public void misEventos(View view){
        Intent sig = new Intent(this, mis_eventos.class);
        sig.putExtra("DATA_EMAIL_KEY",email);
        startActivity(sig);

    }
    public void ventanaPerfil(View view){
        Intent sig = new Intent(this, profile.class);
        sig.putExtra("DATA_EMAIL_KEY",email);
        startActivity(sig);
    }
    public void exit(View view){
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.putExtra("EXIT", true);
        startActivity(i);
    }

}