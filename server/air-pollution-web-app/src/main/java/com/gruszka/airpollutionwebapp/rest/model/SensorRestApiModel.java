package com.gruszka.airpollutionwebapp.rest.model;

import java.util.List;

public class SensorRestApiModel {

    private Integer id;
    private Integer idApi;
    private ParameterRestApiModel parameter;
    private List<PollutionDataRestApiModel> pollutionData;

    public SensorRestApiModel() {
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

    public ParameterRestApiModel getParameter() {
        return parameter;
    }

    public void setParameter(ParameterRestApiModel parameter) {
        this.parameter = parameter;
    }

    public List<PollutionDataRestApiModel> getPollutionData() {
        return pollutionData;
    }

    public void setPollutionData(List<PollutionDataRestApiModel> pollutionData) {
        this.pollutionData = pollutionData;
    }
}
