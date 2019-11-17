package com.gruszka.airpollutionwebapp.dao;

import com.gruszka.airpollutionwebapp.entity.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PollutionDataDao extends JpaRepository<PollutionData, Long> {


    Optional<PollutionData> findByDateAndSensorAndParameter(Date date, Sensor sensor, Parameter parameter);

    @Query("SELECT p FROM PollutionData p JOIN FETCH p.sensor s JOIN FETCH s.station WHERE p.sensor =(:sensor) AND p.value > 0 ORDER BY p.date DESC")
    List<PollutionData> findIndicesFromSensor(@Param("sensor") Sensor sensor, Pageable pageable);

    default PollutionData findMostRecentIndexFromSensor(Sensor sensor){
        List<PollutionData> list =  findIndicesFromSensor(sensor, PageRequest.of(0,1));
        if(list.isEmpty()){
            return null;
        }
        else {
            return list.get(0);
        }
    }

    @Query("SELECT p FROM PollutionData p WHERE p.sensor=(:sensor) AND p.value>0 ORDER BY p.date DESC")
    List<PollutionData> findDataFromSensorOrderByDateDesc(@Param("sensor") Sensor sensor, Pageable pageable);

    default Optional<PollutionData> findMostRecentDataFromSensor(Sensor sensor){
        List<PollutionData> list = findDataFromSensorOrderByDateDesc(sensor, PageRequest.of(0,1));
        if(list.isEmpty()){
            return null;
        }
        else {
            return Optional.of(list.get(0));
        }
    }

}
