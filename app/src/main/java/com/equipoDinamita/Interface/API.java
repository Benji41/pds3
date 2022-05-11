package com.equipoDinamita.Interface;

import com.equipoDinamita.Model.Evento;
import com.equipoDinamita.Model.Medida;
import com.equipoDinamita.Model.User;
import com.equipoDinamita.Model.eventoUsuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface API {
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("/registrarUsuario")
    Call<User> insertUser(@Field("id") String email, @Field("name") String first, @Field("surNames") String last, @Field("age") int age, @Field("picture") String foto);
    @GET("/perfil")
    Call<User> getProfile(@Query("id") String id);
    @GET("/salud")
    Call<User> getHealth(@Query("id") String id);
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @PUT("/bloquearUsuario")
    Call<User> blockUser(@Field("id") String id);
    @GET("/obtenerAsistentes")
    Call<List<User>> getAttendesEvents(@Query("id_email") String id);
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @PUT("/cancelarEventosProximosObtenerAsistentes")
    Call<List<User>> getAttendesUpcomingEvents(@Field("id_email") String id);
    @GET("/eventos")
    Call<List<Evento>> eventos(@Query("id") String id);
    @GET("/eventoMedidas")
    Call<List<Medida>> medidasEventos(@Query("id_ev") int id_ev);
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("/registrarEventoUsuario")
    Call<eventoUsuario> registrarEventoUsuario(@Field("id") String email, @Field("id_ev") int id_ev);

}
