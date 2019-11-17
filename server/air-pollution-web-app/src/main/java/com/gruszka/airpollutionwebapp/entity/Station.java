package com.gruszka.airpollutionwebapp.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="station")
@JsonIgnoreProperties({"sensors", "airQualityIndices"})
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonProperty("id")
    private Integer id;

    @Column(name = "id_api")
    @JsonProperty("id_api")
    private Integer idApi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="service_id")
    @JsonProperty("service")
    private AirQualityService service;

    @Column(name="station_name")
    @JsonProperty("station_name")
    private String stationName;

    @Column(name="gegr_lat")
    @JsonProperty("gegr_lat")
    private Double gegrLat;

    @Column(name = "gegr_lon")
    @JsonProperty("gegr_lon")
    private Double gegrLon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    @JsonProperty("city")
    private City city;

    @Column(name = "street_address")
    @JsonProperty("street_address")
    private String streetAddress;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "station")
    @JsonProperty("sensors")
    private List<Sensor> sensors;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "station")
    @JsonProperty("airQualityIndices")
    private List<AirQualityIndex> airQualityIndices;

    public Station() {
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

    public AirQualityService getService() {
        return service;
    }

    public void setService(AirQualityService service) {
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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public List<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(List<Sensor> sensors) {
        this.sensors = sensors;
    }

    public List<AirQualityIndex> getAirQualityIndices() {
        return airQualityIndices;
    }

    public void setAirQualityIndices(List<AirQualityIndex> airQualityIndices) {
        this.airQualityIndices = airQualityIndices;
    }
}
