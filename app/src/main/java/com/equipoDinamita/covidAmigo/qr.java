package com.equipoDinamita.covidAmigo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class qr extends AppCompatActivity {


    Button button;
    ImageView imgQr;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        button = findViewById(R.id.button2);


        generarQR("x"); //En lugar de "x", debe ir la cadena que se pasa.


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sigV();

            }
        });

    }


    public void sigV(){
        Intent i = new Intent(this, menu.class);
        startActivity(i);
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
}