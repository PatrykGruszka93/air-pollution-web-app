package com.gruszka.airpollutionwebapp.dao;

import com.gruszka.airpollutionwebapp.entity.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ParameterDao extends JpaRepository<Parameter, Integer> {


    Optional<Parameter> findByParameterName(String parameterName);

    Optional<Parameter> findById(Integer id);

    @Query(value = "SELECT DISTINCT p.* FROM data_value_history d " +
            "JOIN parameter p ON d.sensor_parameter_id = p.id " +
            "WHERE d.sensor_id IN ( " +
            "SELECT id FROM sensor " +
            "WHERE station_id IN( " +
            "SELECT id FROM station " +
            "WHERE city_id = ?1" +
            ")" +
            ")" +
            " ORDER BY p.id"
            , nativeQuery = true)
    List<Parameter> findDistinctByCityId(Integer cityId);

}


