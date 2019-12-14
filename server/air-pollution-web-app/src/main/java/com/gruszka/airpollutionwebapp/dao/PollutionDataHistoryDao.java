package com.gruszka.airpollutionwebapp.dao;

import com.gruszka.airpollutionwebapp.entity.Parameter;
import com.gruszka.airpollutionwebapp.entity.PollutionDataHistory;
import com.gruszka.airpollutionwebapp.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface PollutionDataHistoryDao extends JpaRepository<PollutionDataHistory, Long> {

    Optional<PollutionDataHistory> findByDateAndSensorAndParameter(Date date, Sensor sensor, Parameter parameter);

}
