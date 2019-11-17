package com.gruszka.airpollutionwebapp.rest.model;

import java.util.Date;

public class PollutionDataRestApiModel {

    private Long id;
    private ParameterRestApiModel parameter;
    private Date date;
    private Double value;
    private Integer percentValue;
    private IndexRestApiModel index;

    public PollutionDataRestApiModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ParameterRestApiModel getParameter() {
        return parameter;
    }

    public void setParameter(ParameterRestApiModel parameter) {
        this.parameter = parameter;
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

    public Integer getPercentValue() {
        return percentValue;
    }

    public void setPercentValue(Integer percentValue) {
        this.percentValue = percentValue;
    }

    public IndexRestApiModel getIndex() {
        return index;
    }

    public void setIndex(IndexRestApiModel index) {
        this.index = index;
    }
}
