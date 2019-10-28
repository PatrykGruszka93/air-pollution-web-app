package com.gruszka.airpollutionwebapp.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="pollution_data")
public class PollutionData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    private Sensor sensor;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sensor_parameter_id", referencedColumnName = "id")
    private Parameter parameter;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pollutionData")
    private List<PollutionDataValue> pollutionDataValue;

    public PollutionData() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<PollutionDataValue> getPollutionDataValue() {
        return pollutionDataValue;
    }

    public void setPollutionDataValue(List<PollutionDataValue> pollutionDataValue) {
        this.pollutionDataValue = pollutionDataValue;
    }
}
