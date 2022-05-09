package com.equipoDinamita.Interface;

import com.equipoDinamita.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface API {
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("/registrarUsuario")
    Call<User> insertUser(@Field("id") String email, @Field("name") String first, @Field("surNames") String last, @Field("age") int age, @Field("picture") String foto);

   /* @GET("/eventos")
    Call<List<Event>> getEventos(@Query("id") String id);*/

    /*@GET("/eventomedida")
    Call<List<Medida>> getMedidasEventos(@Query("id_evento") int id);*/

    @GET("/eventohost")
    Call<User> getHostEventos(@Query("id_evento") int id);
}