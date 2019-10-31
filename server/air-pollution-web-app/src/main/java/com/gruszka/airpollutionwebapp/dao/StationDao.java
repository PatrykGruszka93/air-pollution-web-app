package com.gruszka.airpollutionwebapp.dao;

import com.gruszka.airpollutionwebapp.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StationDao extends JpaRepository<Station, Long> {


    Optional<Station> findByStationName(String stationName);

}
