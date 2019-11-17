package com.gruszka.airpollutionwebapp.service;

import com.gruszka.airpollutionwebapp.entity.AirQualityService;
import com.gruszka.airpollutionwebapp.entity.Station;
import com.gruszka.airpollutionwebapp.gios.model.StationGIOSModel;
import com.gruszka.airpollutionwebapp.rest.model.StationRestApiModel;

import java.util.List;

public interface StationService {

    List<Station> findAll();

    void save(Station station);

    void saveAll(List<StationGIOSModel> stations);

    Station findByStationName(String stationName);

    Station findByIdApiAndService(int idApi, AirQualityService service);

    List<Station> findAllByService(AirQualityService service);

    List<Station> findStationsBasicDetailsByService(AirQualityService service);

}
