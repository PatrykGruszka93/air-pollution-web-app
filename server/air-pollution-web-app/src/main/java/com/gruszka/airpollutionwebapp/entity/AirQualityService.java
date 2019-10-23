package com.gruszka.airpollutionwebapp.entity;


import javax.persistence.*;

@Entity
@Table(name="service")
public class AirQualityService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Integer id;

    @Column
    private String name;

    public AirQualityService() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
