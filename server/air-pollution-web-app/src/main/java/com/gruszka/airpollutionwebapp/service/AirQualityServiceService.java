package com.gruszka.airpollutionwebapp.service;

import com.gruszka.airpollutionwebapp.entity.AirQualityService;

import java.util.List;

public interface AirQualityServiceService {

    List<AirQualityService> findAll();

    AirQualityService findByName(String name);

}
