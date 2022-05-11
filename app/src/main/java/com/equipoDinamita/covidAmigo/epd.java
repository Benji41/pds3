package com.equipoDinamita.covidAmigo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class epd extends AppCompatActivity{

    Button button;
    ListView listview;
    ArrayList<String> eventos;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_epd);

        button = findViewById(R.id.regresar);
        listview = findViewById(R.id.lv);


        eventos = new ArrayList<String>();
        eventos.add("EVENTO1");
        eventos.add("EVENTO2");
        eventos.add("EVENTO3");



        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, eventos);
        listview.setAdapter(adapter);




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

}