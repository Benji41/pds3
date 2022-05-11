package com.equipoDinamita.covidAmigo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.equipoDinamita.Interface.API;

import com.equipoDinamita.Model.eventoUsuario;
import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class qr extends AppCompatActivity {
    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl("https://covid-amigo-pds3.herokuapp.com/").addConverterFactory(GsonConverterFactory.create());
    public static Retrofit retrofit = builder.build();
    Button button;
    ImageView imgQr;
    String email;
    int id_ev;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
        if (getIntent().hasExtra("DATA_EMAIL_KEY")){
            email =getIntent().getStringExtra("DATA_EMAIL_KEY");
            if(getIntent().hasExtra("DATA_EVENT_KEY")){
                id_ev = getIntent().getIntExtra("DATA_EVENT_KEY", -1);
            }
        }
        button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sigV();
            }
        });
        registerEventUserAPI();
    }

    private void registerEventUserAPI(){
        API registro = retrofit.create(API.class);
        retrofit2.Call<eventoUsuario> Call = registro.registrarEventoUsuario(email,id_ev);
        Call.enqueue(new Callback<eventoUsuario>() {
            @Override
            public void onResponse(retrofit2.Call<eventoUsuario> call, Response<eventoUsuario> response) {
                if(response.isSuccessful()){
                    Toast.makeText(qr.this, "Invitacion almacenada", Toast.LENGTH_LONG).show();
                    generarQR(email+ id_ev); //En lugar de "x", debe ir la cadena que se pasa.
                }else{
                    Toast.makeText(qr.this, response.errorBody().toString(), Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(retrofit2.Call<eventoUsuario> call, Throwable t) {
                Toast.makeText(qr.this, "Error de conexion", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void generarQR(String cadena){

        imgQr = findViewById(R.id.qr);

        try{
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.encodeBitmap(cadena, BarcodeFormat.QR_CODE, 750, 750);
            imgQr.setImageBitmap(bitmap);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void sigV(){

        Intent i = new Intent(this, menu.class);
        i.putExtra("DATA_EMAIL_KEY",email);
        startActivity(i);
    }
}