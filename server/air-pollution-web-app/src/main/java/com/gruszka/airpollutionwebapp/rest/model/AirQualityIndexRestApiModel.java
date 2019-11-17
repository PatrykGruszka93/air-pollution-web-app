package com.gruszka.airpollutionwebapp.rest.model;

import java.util.Date;

public class AirQualityIndexRestApiModel {

    private Integer id;
    private Date calcDate;
    private IndexRestApiModel index;

    public AirQualityIndexRestApiModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCalcDate() {
        return calcDate;
    }

    public void setCalcDate(Date calcDate) {
        this.calcDate = calcDate;
    }

    public IndexRestApiModel getIndex() {
        return index;
    }

    public void setIndex(IndexRestApiModel index) {
        this.index = index;
    }
}
