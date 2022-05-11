package com.equipoDinamita.covidAmigo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.equipoDinamita.Interface.API;
import com.equipoDinamita.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class cuestionario extends AppCompatActivity {

    Button button;
    RadioButton s1,s2,s3,s4,n1,n2,n3,n4;
    String email;
    int id_ev;
    List<User> userListsPrevious;
    List<User> userListsUpcoming;
    User user;
    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl("https://covid-amigo-pds3.herokuapp.com/").addConverterFactory(GsonConverterFactory.create());
    public static Retrofit retrofit = builder.build();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuestionario);
        if (getIntent().hasExtra("DATA_EMAIL_KEY")){
            email =getIntent().getStringExtra("DATA_EMAIL_KEY");
            if(getIntent().hasExtra("DATA_EVENT_KEY")){
                id_ev = getIntent().getIntExtra("DATA_EVENT_KEY", -1);
            }
        }
        s1 = (RadioButton) findViewById(R.id.s1);
        s2 = (RadioButton) findViewById(R.id.s2);
        s3 = (RadioButton) findViewById(R.id.s3);
        s4 = (RadioButton) findViewById(R.id.s4);
        n1 = (RadioButton) findViewById(R.id.n1);
        n2 = (RadioButton) findViewById(R.id.n2);
        n3 = (RadioButton) findViewById(R.id.n3);
        n4 = (RadioButton) findViewById(R.id.n4);
        button = (Button) findViewById(R.id.aceptar);
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
                    blockUser();
                }else{
                    qr();
                }

            }
        });
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
                        Toast.makeText(cuestionario.this, "Error de Back-End", Toast.LENGTH_LONG).show();
                    }
                }
            }
            @Override
            public void onFailure(retrofit2.Call<User> call, Throwable t) {
                Toast.makeText(cuestionario.this, "Error de conexion", Toast.LENGTH_LONG).show();
            }
        });
    }
    private void alert(API alert){
        Call<List<User>> Call = alert.getAttendesEvents(email);
        Call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(retrofit2.Call<List<User>> call, Response<List<User>> response) {
                if(response.isSuccessful()){
                    Toast.makeText(cuestionario.this, response.body().toString(), Toast.LENGTH_LONG).show();
                    userListsPrevious = response.body();
                }else{
                    if(!response.errorBody().toString().isEmpty()){
                        Toast.makeText(cuestionario.this, "Error de Back-End", Toast.LENGTH_LONG).show();
                    }
                }
            }
            @Override
            public void onFailure(retrofit2.Call<List<User>> call, Throwable t) {
                Toast.makeText(cuestionario.this, "previos "+call+" "+t, Toast.LENGTH_LONG).show();
                System.out.println(call+" "+t.toString());
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
                        Toast.makeText(cuestionario.this, "Error de Back-End", Toast.LENGTH_LONG).show();
                    }
                }

            }
            @Override
            public void onFailure(retrofit2.Call<List<User>> call, Throwable t) {
                Toast.makeText(cuestionario.this, "proximos "+call+" "+t, Toast.LENGTH_LONG).show();
            }
        });
        sigV();
    }
    public void sigV(){
        Intent sig;
        sig= new Intent(this, bloq.class);
        startActivity(sig);
    }
    public void menu(){
        Intent i = new Intent(this, menu.class);
        startActivity(i);
    }
    public void qr(){
        Intent i = new Intent(cuestionario.this, qr.class);
        i.putExtra("DATA_EMAIL_KEY",email);
        i.putExtra("DATA_EVENT_KEY",id_ev);
        startActivity(i);
    }
}