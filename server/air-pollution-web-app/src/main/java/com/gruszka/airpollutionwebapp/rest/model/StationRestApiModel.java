package com.gruszka.airpollutionwebapp.rest.model;

public class StationRestApiModel {

    private Integer id;
    private Integer idApi;
    private ServiceRestApiModel service;
    private String stationName;
    private Double gegrLat;
    private Double gegrLon;
    private CityRestApiModel city;
    private String streetAddress;
    private AirQualityIndexRestApiModel aqi;

    public StationRestApiModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdApi() {
        return idApi;
    }

    public void setIdApi(Integer idApi) {
        this.idApi = idApi;
    }

    public ServiceRestApiModel getService() {
        return service;
    }

    public void setService(ServiceRestApiModel service) {
        this.service = service;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public Double getGegrLat() {
        return gegrLat;
    }

    public void setGegrLat(Double gegrLat) {
        this.gegrLat = gegrLat;
    }

    public Double getGegrLon() {
        return gegrLon;
    }

    public void setGegrLon(Double gegrLon) {
        this.gegrLon = gegrLon;
    }

    public CityRestApiModel getCity() {
        return city;
    }

    public void setCity(CityRestApiModel city) {
        this.city = city;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public AirQualityIndexRestApiModel getAqi() {
        return aqi;
    }

    public void setAqi(AirQualityIndexRestApiModel aqi) {
        this.aqi = aqi;
    }
}
