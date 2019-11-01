package com.gruszka.airpollutionwebapp.dao;

import com.gruszka.airpollutionwebapp.entity.AirQualityService;
import com.gruszka.airpollutionwebapp.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StationDao extends JpaRepository<Station, Long> {


    Optional<Station> findByStationName(String stationName);

    Optional<Station> findByIdApiAndService(int idApi, AirQualityService service);

    List<Station> findAllByService(AirQualityService service);

}
