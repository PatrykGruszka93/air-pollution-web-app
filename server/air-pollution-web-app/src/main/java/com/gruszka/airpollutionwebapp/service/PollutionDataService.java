package com.gruszka.airpollutionwebapp.service;

import com.gruszka.airpollutionwebapp.entity.*;
import com.gruszka.airpollutionwebapp.gios.model.PollutionDataGIOSModel;

import java.util.Date;
import java.util.List;

public interface PollutionDataService {

    void save(PollutionData pollutionData);

    void saveAll(PollutionDataGIOSModel pollutionDataModel, Sensor sensor);

    PollutionData findByDateAndSensorAndParameter(Date date, Sensor sensor, Parameter parameter);

    PollutionData findMostRecentIndexFromSensor(Sensor sensor);

    PollutionData findMostRecentDataFromSensor(Sensor sensor);

    List<PollutionDataHistory> findDataForTransferIntoHistoryTable();

    void deleteOldData();



}
