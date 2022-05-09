package com.equipoDinamita.Model;

public class User {
    private String nombre,apellidos,email,foto,covidFechaInicial,getCovidFechaFinal;
    private int edad,salud,asistencias;
    public User() { }

    public User(String nombre, String apellidos, String email, String foto, String covidFechaInicial, String getCovidFechaFinal, int edad, int salud, int asistencias) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.foto = foto;
        this.covidFechaInicial = covidFechaInicial;
        this.getCovidFechaFinal = getCovidFechaFinal;
        this.edad = edad;
        this.salud = salud;
        this.asistencias = asistencias;
    }

    public String getCovidFechaInicial() {
        return covidFechaInicial;
    }

    public void setCovidFechaInicial(String covidFechaInicial) {
        this.covidFechaInicial = covidFechaInicial;
    }

    public String getGetCovidFechaFinal() {
        return getCovidFechaFinal;
    }

    public void setGetCovidFechaFinal(String getCovidFechaFinal) {
        this.getCovidFechaFinal = getCovidFechaFinal;
    }

    public int getSalud() {
        return salud;
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }

    public int getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(int asistencias) {
        this.asistencias = asistencias;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "User{" +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", email='" + email + '\'' +
                ", foto='" + foto + '\'' +
                ", covidFechaInicial='" + covidFechaInicial + '\'' +
                ", getCovidFechaFinal='" + getCovidFechaFinal + '\'' +
                ", edad=" + edad +
                ", salud=" + salud +
                ", asistencias=" + asistencias +
                '}';
    }
}
