package com.gruszka.airpollutionwebapp.entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="station")
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="service_id")
    private AirQualityService service;

    @Column(name="station_name")
    private String stationName;

    @Column(name="gegr_lat")
    private Double gegrLat;

    @Column(name = "gegr_lon")
    private Double gegrLon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    @Column(name = "street_address")
    private String streetAddress;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "station")
    private List<Sensor> sensors;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "station")
    private List<PollutionIndexLevel> pollutionIndexLevels;

    public Station() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<PollutionIndexLevel> getPollutionIndexLevels() {
        return pollutionIndexLevels;
    }

    public void setPollutionIndexLevels(List<PollutionIndexLevel> pollutionIndexLevels) {
        this.pollutionIndexLevels = pollutionIndexLevels;
    }
}
