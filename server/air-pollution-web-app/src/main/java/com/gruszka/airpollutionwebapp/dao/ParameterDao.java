package com.gruszka.airpollutionwebapp.dao;

import com.gruszka.airpollutionwebapp.entity.Parameter;
import com.gruszka.airpollutionwebapp.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParameterDao extends JpaRepository<Parameter, Integer> {


    Optional<Parameter> findByParameterName(String parameterName);

    Optional<Parameter> findById(Integer id);

}
