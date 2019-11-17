package com.gruszka.airpollutionwebapp.service;

import com.gruszka.airpollutionwebapp.entity.AirQualityIndex;
import com.gruszka.airpollutionwebapp.entity.PollutionData;
import com.gruszka.airpollutionwebapp.entity.Station;

import java.util.Date;

public interface AirQualityIndexService {

    void save(AirQualityIndex airQualityIndex);
    AirQualityIndex findById(Long id);
    AirQualityIndex findByCalcDateAndStation(Date calcDate, Station station);


    void saveIndexFromMostPollutedSensor(PollutionData mostPollutedSensor);
}
