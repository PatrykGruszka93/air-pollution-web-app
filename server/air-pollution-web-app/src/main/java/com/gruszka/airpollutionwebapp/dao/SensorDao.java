package com.gruszka.airpollutionwebapp.dao;

import com.gruszka.airpollutionwebapp.entity.Index;
import com.gruszka.airpollutionwebapp.entity.Parameter;
import com.gruszka.airpollutionwebapp.entity.Sensor;
import com.gruszka.airpollutionwebapp.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SensorDao extends JpaRepository<Sensor, Long> {

    List<Sensor> findAllByStationId(Integer id);

    Optional<Sensor> findByIdApiAndStation(int id, Station station);

    Optional<Sensor> findById(Integer id);

    List<Sensor> findAllByStation(Station station);

    @Query("SELECT s.parameter FROM Sensor s WHERE s.id = (:id)")
    Optional<Parameter> findParameterBySensorId(@Param("id") Integer id);



}
