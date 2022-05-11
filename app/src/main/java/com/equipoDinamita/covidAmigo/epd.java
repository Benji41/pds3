package com.equipoDinamita.covidAmigo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.equipoDinamita.Interface.API;
import com.equipoDinamita.Model.Evento;
import com.equipoDinamita.Model.Medida;
import com.equipoDinamita.Model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class epd extends AppCompatActivity{

    Button button;
    ListView listview;
    List<Evento> eventos;
    String email;
    String measuresS = "";
    AlertDialog.Builder builder;
    CharSequence[] dialogItem;
    private static Retrofit.Builder builderR = new Retrofit.Builder().baseUrl("https://covid-amigo-pds3.herokuapp.com/").addConverterFactory(GsonConverterFactory.create());
    public static Retrofit retrofit = builderR.build();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_epd);

        button = findViewById(R.id.regresar);
        listview = findViewById(R.id.lv);
        if (getIntent().hasExtra("DATA_EMAIL_KEY")){
            email =getIntent().getStringExtra("DATA_EMAIL_KEY");
        }
        getUpcomingEventsAPI();
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

    private void getUpcomingEventsAPI() {
        API events = retrofit.create(API.class);
        retrofit2.Call<List<Evento>> Call = events.eventos(email);
        Call.enqueue(new Callback<List<Evento>>() {
            @Override
            public void onResponse(retrofit2.Call<List<Evento>> call, Response<List<Evento>> response) {
                if(response.isSuccessful()){
                    eventos = response.body();
                    List<String> eventosString = new ArrayList<>();
                    for (Evento e: eventos){
                        eventosString.add(e.getEv_name()+" "+e.getEv_date());
                    }
                    ArrayAdapter adapter = new ArrayAdapter<String>(epd.this, android.R.layout.simple_list_item_1, eventosString);
                    listview.setAdapter(adapter);
                    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                            measuresS = "";
                            builder = new AlertDialog.Builder(view.getContext());
                            getMeasuresEvent(eventos.get(position).getId_ev(),position);

                        }
                    });
                }else{
                    if(!response.errorBody().toString().isEmpty()){
                        Toast.makeText(epd.this, response.body().toString(), Toast.LENGTH_LONG).show();
                    }
                }
            }
            @Override
            public void onFailure(retrofit2.Call<List<Evento>> call, Throwable t) {
                Toast.makeText(epd.this, "Error de conexion", Toast.LENGTH_LONG).show();
            }
        });
    }
    private void getMeasuresEvent(int id_ev,int position){

        API measures = retrofit.create(API.class);
        retrofit2.Call<List<Medida>> Call = measures.medidasEventos(id_ev);
        Call.enqueue(new Callback<List<Medida>>() {
            @Override
            public void onResponse(retrofit2.Call<List<Medida>> call, Response<List<Medida>> response) {
                if (response != null){
                    for (int i = 0; i < response.body().size(); i++) {
                            measuresS+="\n"+(i+1)+". "+response.body().get(i).getMe_desc();
                    }
                    if(!measuresS.isEmpty()){
                       dialogItem = new CharSequence[]{"Descripcion: " + eventos.get(position).getEv_desc() + '\n' + "Fecha: " + eventos.get(position).getEv_date() + '\n' + "Direccion: "
                               + eventos.get(position).getEv_loc() + '\n' + "Entorno: " + checarEntorno(eventos.get(position).getEv_enviro()) + '\n' + "Foro: " + eventos.get(position).getEv_cap() +
                               '\n' + "Estado: " + checarEstado(eventos.get(position).getEv_status()) + '\n' + "Medidas sanitarias: " + measuresS, "Asistir"};

                    }
                }else{
                    if(!response.errorBody().toString().isEmpty()){
                        Toast.makeText(epd.this, response.body().toString(), Toast.LENGTH_LONG).show();
                    }
                }
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        Intent intent = new Intent(getApplicationContext(), cuestionario.class);
                        intent.putExtra("DATA_EMAIL_KEY",email);
                        intent.putExtra("DATA_EVENT_KEY",eventos.get(position).getId_ev());
                        startActivity(intent);
                    }
                });
                builder.create().show();
            }
            @Override
            public void onFailure(retrofit2.Call<List<Medida>> call, Throwable t) {
                    dialogItem = new CharSequence[]{"Descripcion: " + eventos.get(position).getEv_desc() + '\n' + "Fecha: " + eventos.get(position).getEv_date() + '\n' + "Direccion: "
                            + eventos.get(position).getEv_loc() + '\n' + "Entorno: " + checarEntorno(eventos.get(position).getEv_enviro())  + '\n' + "Foro: " + eventos.get(position).getEv_cap() +
                            '\n' + "Estado: " + checarEstado(eventos.get(position).getEv_status())+'\n' + "Medidas sanitarias: No hay medidas disponibles", "Asistir"};

                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        Intent intent = new Intent(getApplicationContext(), cuestionario.class);
                        intent.putExtra("DATA_EMAIL_KEY",email);
                        intent.putExtra("DATA_EVENT_KEY",eventos.get(position).getId_ev());
                        startActivity(intent);
                    }
                });
                builder.create().show();

            }
        });

    }
    private String checarEntorno(int entorno){
        if(entorno==0){
            return "Lugar abierto";
        }else{
            return "Lugar cerrado";
        }
    }
    private String checarEstado(int estado){
        String estadoS;
        switch (estado){
            case 0:
                estadoS= "En curso";
                break;
            case  1:
                estadoS="Cancelado";
                break;
            case 2:
                estadoS="Actualizandose";
                break;
            case 3:
                estadoS="Concluido";
                break;
            default:
                estadoS="estado no existente";
                break;
        }
        return estadoS;
    }

}