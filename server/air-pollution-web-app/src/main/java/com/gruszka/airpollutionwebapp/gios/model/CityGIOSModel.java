package com.gruszka.airpollutionwebapp.gios.model;

public class CityGIOSModel {

    private int id;
    private String name;
    private CommuneGIOSModel communeGIOSModel;

    public CityGIOSModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CommuneGIOSModel getCommune() {
        return communeGIOSModel;
    }

    public void setCommune(CommuneGIOSModel communeGIOSModel) {
        this.communeGIOSModel = communeGIOSModel;
    }
}
