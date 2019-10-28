package com.gruszka.airpollutionwebapp.gios.model;

public class SensorGIOSModel {

    private int id;
    private int stationId;
    private ParameterGIOSModel param;

    public SensorGIOSModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public ParameterGIOSModel getParam() {
        return param;
    }

    public void setParam(ParameterGIOSModel parameter) {
        this.param = parameter;
    }
}
