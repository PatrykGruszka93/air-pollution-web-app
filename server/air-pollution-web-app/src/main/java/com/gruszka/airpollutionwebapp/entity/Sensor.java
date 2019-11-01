package com.gruszka.airpollutionwebapp.entity;

import javax.persistence.*;

@Entity
@Table(name="sensor")
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "id_api")
    private Integer idApi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_id")
    private Station station;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parameter_id")
    private Parameter parameter;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "sensor")
    private PollutionData pollutionData;

    public Sensor() {
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

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Parameter getParameter() {
        return parameter;
    }

    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
    }

    public PollutionData getPollutionData() {
        return pollutionData;
    }

    public void setPollutionData(PollutionData pollutionData) {
        this.pollutionData = pollutionData;
    }
}
