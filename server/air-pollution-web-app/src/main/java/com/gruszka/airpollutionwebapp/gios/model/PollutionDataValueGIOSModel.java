package com.gruszka.airpollutionwebapp.gios.model;

public class PollutionDataValueGIOSModel {

    private String date;
    private double value;

    public PollutionDataValueGIOSModel() {
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
