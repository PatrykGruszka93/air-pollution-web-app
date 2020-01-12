package com.gruszka.airpollutionwebapp.rest.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class PollutionDataHistoryRestApiModel {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;
    private Double value;

    public PollutionDataHistoryRestApiModel() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
