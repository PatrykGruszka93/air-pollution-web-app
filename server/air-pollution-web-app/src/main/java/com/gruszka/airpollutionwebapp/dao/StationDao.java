package com.gruszka.airpollutionwebapp.dao;

import com.gruszka.airpollutionwebapp.entity.AirQualityService;
import com.gruszka.airpollutionwebapp.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StationDao extends JpaRepository<Station, Long> {


    Optional<Station> findByStationName(String stationName);

    Optional<Station> findByIdApiAndService(int idApi, AirQualityService service);

    //@Query("SELECT s from Station s JOIN FETCH s.sensors WHERE s.service = (:service)")
    List<Station> findAllByService(@Param("service") AirQualityService service);

    @Query("SELECT s from Station s JOIN FETCH s.city c JOIN FETCH c.commune WHERE s.service = (:service)")
    List<Station> findStationsBasicDetailsByService(@Param("service") AirQualityService service);




}
