package com.gruszka.airpollutionwebapp.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="data_value")
public class PollutionDataValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    private Date date;

    @Column(name = "value")
    private Double value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pollution_data_id")
    private PollutionData pollutionData;

    public PollutionDataValue(Date date) {
        this.date = date;
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

    public PollutionData getPollutionData() {
        return pollutionData;
    }

    public void setPollutionData(PollutionData pollutionData) {
        this.pollutionData = pollutionData;
    }
}
