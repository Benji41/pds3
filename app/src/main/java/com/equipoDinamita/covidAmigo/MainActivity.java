package com.equipoDinamita.covidAmigo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.equipoDinamita.Interface.API;
import com.equipoDinamita.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText mTextEmail, mTextPassword;
    Button mBSignIn;
    TextView mTForgot, mTRegister;
    User user;
    private FirebaseAuth mAuth;
    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl("https://covid-amigo-pds3.herokuapp.com/").addConverterFactory(GsonConverterFactory.create());
    public static Retrofit retrofit = builder.build();
    //private ProgressBar progressBar;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextEmail = (EditText) findViewById(R.id.EmailAddress);
        mTextPassword = (EditText) findViewById(R.id.Password);
        //progressBar = (ProgressBar) findViewById(R.id.progressBar);
        mBSignIn = findViewById(R.id.signIn);
        mAuth = FirebaseAuth.getInstance();
        mTForgot = findViewById(R.id.forgotPassword);
        mTRegister = findViewById(R.id.register);
        mTRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sigV3();
            }
        });

        mBSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });

        mTForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sigV2();

            }
        });




        if(getIntent().getBooleanExtra("EXIT",false)){
            finish();
        }

    }
    private void userLogin() {
        String email = mTextEmail.getText().toString().trim();
        String password = mTextPassword.getText().toString().trim();

        if(email.isEmpty()){
            mTextEmail.setError("Email es requerido!");
            mTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            System.out.println(email);
            mTextEmail.setError("Porfavor ingresa un email valido");
            mTextEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            mTextPassword.setError("Contrasena es requerida!");
            mTextPassword.requestFocus();
            return;
        }
        if(password.length() < 6){
            mTextPassword.setError("Min password length is 6 characters!");
            mTextPassword.requestFocus();
            return;
        }

        //progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    if(user.isEmailVerified()){
                        getUserHealthAPI();
                    }else{
                        user.sendEmailVerification();
                        Toast.makeText(MainActivity.this, "Verifica tu email!",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this, "Falla al iniciar sesión!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void getUserHealthAPI() {
        API health = retrofit.create(API.class);
        retrofit2.Call<User> Call = health.getHealth(mTextEmail.getText().toString());
        Call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(retrofit2.Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    user = response.body();
                    Toast.makeText(MainActivity.this, response.body().toString(), Toast.LENGTH_LONG).show();
                    sigV();
                }else{
                    if(!response.errorBody().toString().isEmpty()){
                        Toast.makeText(MainActivity.this, "Error de Back-End", Toast.LENGTH_LONG).show();
                    }
                }
            }
            @Override
            public void onFailure(retrofit2.Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error de conexion", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void sigV(){
        Intent sig = new Intent(this, menu.class);
        sig.putExtra("DATA_HEALTH_KEY",user.getUs_health());
        sig.putExtra("DATA_EMAIL_KEY",mTextEmail.getText().toString());
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