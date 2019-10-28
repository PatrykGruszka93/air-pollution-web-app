package com.gruszka.airpollutionwebapp.gios.model;

public class PollutionDataValue {

    private String date;
    private double value;

    public PollutionDataValue() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
