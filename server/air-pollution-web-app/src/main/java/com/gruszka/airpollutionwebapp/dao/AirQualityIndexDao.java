package com.gruszka.airpollutionwebapp.dao;

import com.gruszka.airpollutionwebapp.entity.AirQualityIndex;
import com.gruszka.airpollutionwebapp.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface AirQualityIndexDao extends JpaRepository<AirQualityIndex, Long> {


    Optional<AirQualityIndex> findByCalcDateAndStation(Date calcDate, Station station);

}
