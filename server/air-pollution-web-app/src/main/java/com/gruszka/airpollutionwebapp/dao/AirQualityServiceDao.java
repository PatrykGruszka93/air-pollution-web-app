package com.gruszka.airpollutionwebapp.dao;

import com.gruszka.airpollutionwebapp.entity.AirQualityService;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AirQualityServiceDao extends JpaRepository<AirQualityService, Long> {


}
