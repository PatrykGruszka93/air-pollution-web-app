package com.gruszka.airpollutionwebapp.dao;

import com.gruszka.airpollutionwebapp.entity.AirQualityService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AirQualityServiceDao extends JpaRepository<AirQualityService, Long> {

    Optional<AirQualityService> findByName(String name);

}
