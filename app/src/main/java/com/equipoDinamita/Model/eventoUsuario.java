package com.equipoDinamita.Model;

public class eventoUsuario {
    int id_eu;
    String eu_checkin;

    public int getId_eu() {
        return id_eu;
    }

    public void setId_eu(int id_eu) {
        this.id_eu = id_eu;
    }

    public String getEu_checkin() {
        return eu_checkin;
    }

    public void setEu_checkin(String eu_checkin) {
        this.eu_checkin = eu_checkin;
    }

    public eventoUsuario(int id_eu, String eu_checkin) {
        this.id_eu = id_eu;
        this.eu_checkin = eu_checkin;
    }
}
