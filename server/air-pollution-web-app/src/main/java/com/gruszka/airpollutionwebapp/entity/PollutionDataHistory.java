package com.gruszka.airpollutionwebapp.entity;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "data_value_history")
public class PollutionDataHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    private Date date;

    @Column(name = "value")
    private Double value;

    @Column(name = "percent_value")
    private Integer percentValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sensor_id")
    private Sensor sensor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sensor_parameter_id")
    private Parameter parameter;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "index_id")
    private Index index;

    public PollutionDataHistory() {
    }

    public PollutionDataHistory(String date, Double value, Sensor sensor, Parameter parameter) {
        try {
            this.date = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            this.date = null;
        }
        this.value = value;
        this.sensor = sensor;
        this.parameter = parameter;
    }

    public PollutionDataHistory(String date, Double value) {
        try {
            this.date = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            this.date = null;
        }
        this.value = value;
    }

    public PollutionDataHistory(Date date, Double value) {
        this.date = date;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public Parameter getParameter() {
        return parameter;
    }

    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
    }

    public Index getIndex() {
        return index;
    }

    public void setIndex(Index index) {
        this.index = index;
    }
}

