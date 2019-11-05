package com.gruszka.airpollutionwebapp.dao;

import com.gruszka.airpollutionwebapp.entity.Parameter;
import com.gruszka.airpollutionwebapp.entity.PollutionData;
import com.gruszka.airpollutionwebapp.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface PollutionDataDao extends JpaRepository<PollutionData, Long> {


    Optional<PollutionData> findByDateAndSensorAndParameter(Date date, Sensor sensor, Parameter parameter);


}
