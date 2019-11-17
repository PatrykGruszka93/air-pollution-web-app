package com.gruszka.airpollutionwebapp.rest.model;

public class CityRestApiModel {

    private Integer id;
    private String name;
    private CommuneRestApiModel commune;

    public CityRestApiModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CommuneRestApiModel getCommune() {
        return commune;
    }

    public void setCommune(CommuneRestApiModel commune) {
        this.commune = commune;
    }
}
