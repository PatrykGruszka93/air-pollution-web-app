package com.gruszka.airpollutionwebapp.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="index_const")
public class Index {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "index_level_name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "index")
    private List<PollutionData> pollutionDataList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "index")
    private List<PollutionDataHistory> pollutionDataHistoryList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "index")
    private List<AirQualityIndex> airQualityIndices;

    public Index() {
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

    public List<AirQualityIndex> getAirQualityIndices() {
        return airQualityIndices;
    }

    public void setAirQualityIndices(List<AirQualityIndex> airQualityIndices) {
        this.airQualityIndices = airQualityIndices;
    }
}
