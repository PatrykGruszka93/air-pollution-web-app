package com.gruszka.airpollutionwebapp.entity;

import javax.persistence.*;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parameter_id")
    private Parameter parameter;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sensor")
    private List<PollutionData> pollutionDataList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sensor")
    private List<PollutionDataHistory> pollutionDataHistoryList;

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

    public List<PollutionData> getPollutionDataList() {
        return pollutionDataList;
    }

    public void setPollutionDataList(List<PollutionData> pollutionDataList) {
        this.pollutionDataList = pollutionDataList;
    }

    public List<PollutionDataHistory> getPollutionDataHistoryList() {
        return pollutionDataHistoryList;
    }

    public void setPollutionDataHistoryList(List<PollutionDataHistory> pollutionDataHistoryList) {
        this.pollutionDataHistoryList = pollutionDataHistoryList;
    }
}
