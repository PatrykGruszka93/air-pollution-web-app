package com.gruszka.airpollutionwebapp.entity;

import javax.persistence.*;

@Entity
@Table(name="parameter")
public class Parameter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "param_name")
    private String parameterName;

    @Column(name = "param_formula")
    private String parameterFormula;

    @Column(name = "param_code")
    private String parameterCode;

    public Parameter() {
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

    public String getParameterFormula() {
        return parameterFormula;
    }

    public void setParameterFormula(String parameterFormula) {
        this.parameterFormula = parameterFormula;
    }

    public String getParameterCode() {
        return parameterCode;
    }

    public void setParameterCode(String parameterCode) {
        this.parameterCode = parameterCode;
    }
}
