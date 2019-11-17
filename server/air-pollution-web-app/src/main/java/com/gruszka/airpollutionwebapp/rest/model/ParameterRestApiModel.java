package com.gruszka.airpollutionwebapp.rest.model;

public class ParameterRestApiModel {

    private Integer id;
    private String parameterName;
    private String paramteterFormula;

    public ParameterRestApiModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getParamteterFormula() {
        return paramteterFormula;
    }

    public void setParamteterFormula(String paramteterFormula) {
        this.paramteterFormula = paramteterFormula;
    }
}
