package com.equipoDinamita.Model;

public class User {
    private String us_l_name,us_f_name,id_us,us_photo, us_s_symp_period,us_e_symp_period,us_registration_date;
    private int us_age,us_health,us_assisted;
    public User() { }

    public User(String email,String nombre, String apellidos, int edad,  String covidFechaInicial, String getCovidFechaFinal, String registro, String foto,int asistencias,int salud) {
        this.us_f_name= nombre;
        this.us_l_name = apellidos;
        this.id_us = email;
        this.us_photo= foto;
        this.us_s_symp_period = covidFechaInicial;
        this.us_e_symp_period = getCovidFechaFinal;
        this.us_age= edad;
        this.us_health = salud;
        this.us_assisted= asistencias;
        this.us_registration_date = registro;
    }

    public String getUs_l_name() {
        return us_l_name;
    }

    public void setUs_l_name(String us_l_name) {
        this.us_l_name = us_l_name;
    }

    public String getUs_f_name() {
        return us_f_name;
    }

    public void setUs_f_name(String us_f_name) {
        this.us_f_name = us_f_name;
    }

    public String getId_us() {
        return id_us;
    }

    public void setId_us(String id_us) {
        this.id_us = id_us;
    }

    public String getUs_photo() {
        return us_photo;
    }

    public void setUs_photo(String us_photo) {
        this.us_photo = us_photo;
    }

    public String getUs_s_symp_period() {
        return us_s_symp_period;
    }

    public void setUs_s_symp_period(String us_s_symp_period) {
        this.us_s_symp_period = us_s_symp_period;
    }

    public String getUs_e_symp_period() {
        return us_e_symp_period;
    }

    public void setUs_e_symp_period(String us_e_symp_period) {
        this.us_e_symp_period = us_e_symp_period;
    }

    public String getUs_registration_date() {
        return us_registration_date;
    }

    public void setUs_registration_date(String us_registration_date) {
        this.us_registration_date = us_registration_date;
    }

    public int getUs_age() {
        return us_age;
    }

    public void setUs_age(int us_age) {
        this.us_age = us_age;
    }

    public int getUs_health() {
        return us_health;
    }

    public void setUs_health(int us_health) {
        this.us_health = us_health;
    }

    public int getUs_assisted() {
        return us_assisted;
    }

    public void setUs_assisted(int us_assisted) {
        this.us_assisted = us_assisted;
    }

    @Override
    public String toString() {
        return "User{" +
                "us_l_name='" + us_l_name + '\'' +
                ", us_f_name='" + us_f_name + '\'' +
                ", id_us='" + id_us + '\'' +
                ", us_photo='" + us_photo + '\'' +
                ", us_s_symp_period='" + us_s_symp_period + '\'' +
                ", us_e_symp_period='" + us_e_symp_period + '\'' +
                ", us_registration_date='" + us_registration_date + '\'' +
                ", us_age=" + us_age +
                ", us_health=" + us_health +
                ", us_assisted=" + us_assisted +
                '}';
    }
}
