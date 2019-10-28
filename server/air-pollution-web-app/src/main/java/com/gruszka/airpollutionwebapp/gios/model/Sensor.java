package com.gruszka.airpollutionwebapp.gios.model;

public class Sensor {

    private int id;
    private int stationId;
    private Parameter param;

    public Sensor() {
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

    public Parameter getParam() {
        return param;
    }

    public void setParam(Parameter parameter) {
        this.param = parameter;
    }
}
