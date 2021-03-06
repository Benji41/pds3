package com.equipoDinamita.covidAmigo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.ProgressBar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.equipoDinamita.Interface.API;
import com.equipoDinamita.Model.User;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class register_user extends AppCompatActivity {

    Button buttonBack, buttonRegister;
    EditText etEmail, etName,etSurname,etAge,etPassword,etPassword2;
    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl("https://covid-amigo-pds3.herokuapp.com/").addConverterFactory(GsonConverterFactory.create());
    public static Retrofit retrofit = builder.build();
    private User user;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

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
        etPassword2 = (EditText) findViewById(R.id.pass2);

        mAuth = FirebaseAuth.getInstance();
        progressBar = (ProgressBar)  findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = new User(etEmail.getText().toString(),etName.getText().toString(),etSurname.getText().toString(),validarEdad(),"","","","",0,0);
                if(validarCredencciales()){
                    validarfirebase();
                }

            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sigV();
            }
        });
    }
    private void validarfirebase (){
        mAuth.createUserWithEmailAndPassword(user.getId_us(), etPassword.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful()){
                                        registerUserAPI(user.getId_us(),user.getUs_f_name(),user.getUs_l_name(),user.getUs_age(),user.getUs_photo());
                                        progressBar.setVisibility(View.VISIBLE);
                                        //Redirct to Login
                                        sigV();
                                    }else{
                                        Toast.makeText(register_user.this, "Fallo al registrar! Intenta de nuevo ", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }

                            });
                        }else{
                            if(task.getException().toString().contains(" The email address is already in use by another account.")){
                                System.out.println(task.getException());
                                Toast.makeText(register_user.this, "\n ERROR! \n Algo salio mal, intente de nuevo!", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    }
                });
    }
    private int validarEdad(){
        if (etAge.getText().toString().isEmpty()) {
            return 0;
        }else{
            return Integer.parseInt(etAge.getText().toString());
        }
    }
    private Boolean validarCredencciales(){
        Boolean ready=true;

        if((user.getUs_f_name().isEmpty())){
            this.etName.setError("Su nombre es requerido!");
            this.etName.requestFocus();
            ready = false;
        }

        if(user.getUs_l_name().isEmpty()){
            this.etSurname.setError("Sus appellidos son requeridos!");
            this.etSurname.requestFocus();
            ready = false;
        }

        if(this.etAge.getText().toString().isEmpty()){
            this.etAge.setError("Su edad es requerida!");
            this.etAge.requestFocus();
            ready = false;
        }

        if(user.getId_us().isEmpty()){
            this.etEmail.setError("Su correo electronico es requerido!");
            this.etEmail.requestFocus();
            ready = false;
        }

        if(user.getUs_age() == 0){
            this.etAge.setText("");
            this.etAge.setError("Su edad es requerida!");
            this.etAge.requestFocus();
            ready = false;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(user.getId_us()).matches()){
            this.etEmail.setError("Por favor introduzca un correo electronico valido");
            this.etEmail.requestFocus();
            ready = false;
        }

        if(this.etPassword.getText().toString().isEmpty()){
            this.etPassword.setError("Establezca una contrase??a!");
            this.etPassword.requestFocus();
            ready = false;
        }



        if(this.etPassword.getText().toString().length() <6 ){
            this.etPassword.setError("Su contrase??a debe de contener un minimo de 6 caracteres");
            this.etPassword.requestFocus();
            ready = false;
        }

        if(!this.etPassword2.getText().toString().equals(this.etPassword.getText().toString())){
            etPassword2.setError("Las contrase??as no coinciden!");
            etPassword.setError("Las contrase??as no coinciden!");
            this.etPassword2.requestFocus();
            ready = false;
        }
    return ready;
    }
    private void registerUserAPI(String email, String name, String lastNames, Integer age, String foto){
        API RegistroAPI = retrofit.create(API.class);
        Call<User> Call = RegistroAPI.insertUser(email, name, lastNames, age,foto);
        Call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(retrofit2.Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    Toast.makeText(register_user.this, "Registro terminado", Toast.LENGTH_LONG).show();
                }else{
                    if(!response.errorBody().toString().isEmpty()){
                        try {
                            Toast.makeText(register_user.this, response.errorBody().string(), Toast.LENGTH_LONG).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
            @Override
            public void onFailure(retrofit2.Call<User> call, Throwable t) {
                Toast.makeText(register_user.this, t.toString(), Toast.LENGTH_LONG).show();

            }
        });

    }
    public void sigV(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}