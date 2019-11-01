package com.gruszka.airpollutionwebapp.service;

import com.gruszka.airpollutionwebapp.entity.Sensor;
import com.gruszka.airpollutionwebapp.entity.Station;
import com.gruszka.airpollutionwebapp.gios.model.SensorGIOSModel;

import java.util.List;

public interface SensorService {

    void save(Sensor sensor);


    void saveAll(List<SensorGIOSModel> sensors);
    Sensor findByIdApiAndStation(int idApi, Station station);

}
