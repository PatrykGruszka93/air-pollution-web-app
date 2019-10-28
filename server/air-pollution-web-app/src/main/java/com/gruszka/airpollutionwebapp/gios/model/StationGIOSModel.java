package com.gruszka.airpollutionwebapp.gios.model;

public class StationGIOSModel {


    private int id;
    private String stationName;
    private double gegrLat;
    private double gegrLon;
    private CityGIOSModel cityGIOSModel;
    private String addressStreet;

    public StationGIOSModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public double getGegrLat() {
        return gegrLat;
    }

    public void setGegrLat(double gegrLat) {
        this.gegrLat = gegrLat;
    }

    public double getGegrLon() {
        return gegrLon;
    }

    public void setGegrLon(double gegrLon) {
        this.gegrLon = gegrLon;
    }

    public CityGIOSModel getCity() {
        return cityGIOSModel;
    }

    public void setCity(CityGIOSModel cityGIOSModel) {
        this.cityGIOSModel = cityGIOSModel;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }
}
