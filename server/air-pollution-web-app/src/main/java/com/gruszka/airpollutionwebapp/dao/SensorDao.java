package com.gruszka.airpollutionwebapp.dao;

import com.gruszka.airpollutionwebapp.entity.Sensor;
import com.gruszka.airpollutionwebapp.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SensorDao extends JpaRepository<Sensor, Long> {


    Optional<Sensor> findByIdApiAndStation(int id, Station station);
}
