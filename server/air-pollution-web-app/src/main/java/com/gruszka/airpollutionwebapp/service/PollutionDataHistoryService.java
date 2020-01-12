package com.gruszka.airpollutionwebapp.service;

import com.gruszka.airpollutionwebapp.entity.City;
import com.gruszka.airpollutionwebapp.entity.Parameter;
import com.gruszka.airpollutionwebapp.entity.PollutionDataHistory;
import com.gruszka.airpollutionwebapp.entity.Sensor;

import java.util.Date;
import java.util.List;

public interface PollutionDataHistoryService {

    void transferDataIntoDataHistory();

    void save(PollutionDataHistory data);

    PollutionDataHistory findByDateAndSensorAndParameter(Date date, Sensor sensor, Parameter parameter);

    List<PollutionDataHistory> findAvgDataByCityAndDateAndParameter(City city, Date date, Parameter parameter, String datePattern);

}
