package com.equipoDinamita.Model;

public class Evento {

    private String ev_name,ev_desc,ev_date,ev_loc,ev_host;
    private int id_ev,ev_cap,ev_enviro,ev_conf_inv,ev_status,ev_min_age;

    public Evento() { }

    public Evento(int id,String nombre, String descripcion, String fecha, String lugar, int capacidad, int cerrado_abierto, int invitados_confirmados, int status, int edad_minima, String email_host) {
        this.id_ev = id;
        this.ev_name = nombre;
        this.ev_desc = descripcion;
        this.ev_date = fecha;
        this.ev_loc = lugar;
        this.ev_cap = capacidad;
        this.ev_enviro = cerrado_abierto;
        this.ev_conf_inv = invitados_confirmados;
        this.ev_status = status;
        this.ev_min_age = edad_minima;
        this.ev_host = email_host;
    }

    public int getId_ev() {
        return id_ev;
    }

    public void setId_ev(int id_ev) {
        this.id_ev = id_ev;
    }

    public String getEv_name(){return ev_name;}
    public void setEv_name(String ev_name){this.ev_name = ev_name;}

    public String getEv_desc(){return ev_desc;}
    public void setEv_desc(String ev_desc){this.ev_desc = ev_desc;}

    public String getEv_date(){return ev_date;}
    public void setEv_date(String ev_date){this.ev_date = ev_date;}

    public String getEv_loc(){return ev_loc;}
    public void setEv_loc(String ev_loc){this.ev_loc = ev_loc;}

    public int getEv_cap(){return ev_cap;}
    public void setEv_cap(int ev_cap){this.ev_cap = ev_cap;}

    public int getEv_enviro(){return ev_enviro;}
    public void setEv_enviro(int ev_enviro){this.ev_enviro = ev_enviro;}

    public int getEv_conf_inv(){return ev_conf_inv;}
    public void setEv_conf_inv(int ev_conf_inv){this.ev_conf_inv = ev_conf_inv;}

    public int getEv_status(){return ev_status;}
    public void setEv_status(int ev_status){this.ev_status = ev_status;}

    public int getEv_min_age(){return ev_min_age;}
    public void setEv_min_age(int ev_min_age){this.ev_min_age = ev_min_age;}

    public String getEv_host(){return ev_host;}
    public void setEv_host(String ev_host){this.ev_host = ev_host;}

    @Override
    public String toString() {
        return "Evento{" +
                "ev_name='" + ev_name + '\'' +
                ", ev_desc='" + ev_desc + '\'' +
                ", ev_date='" + ev_date + '\'' +
                ", ev_loc='" + ev_loc + '\'' +
                ", ev_host='" + ev_host + '\'' +
                ", id_ev=" + id_ev +
                ", ev_cap=" + ev_cap +
                ", ev_enviro=" + ev_enviro +
                ", ev_conf_inv=" + ev_conf_inv +
                ", ev_status=" + ev_status +
                ", ev_min_age=" + ev_min_age +
                '}';
    }
}
