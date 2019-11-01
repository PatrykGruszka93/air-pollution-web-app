package com.gruszka.airpollutionwebapp.service;

import com.gruszka.airpollutionwebapp.entity.Station;
import com.gruszka.airpollutionwebapp.gios.GIOSApiMapper;
import com.gruszka.airpollutionwebapp.gios.model.SensorGIOSModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SensorServiceImplTest {


    @Autowired
    SensorService sensorService;

    @Autowired
    StationService stationService;

    @Autowired
    AirQualityServiceService airQualityServiceService;

    @Autowired
    GIOSApiMapper giosApiMapper;


    @Test
    public void shouldPopulateSensorTableByOneRowFromGIOSApi(){

        List<SensorGIOSModel> sensorList = giosApiMapper.getSensorsFromStation(117);
        sensorService.saveAll(sensorList);
    }

    @Test
    public void shouldPopulateAllAvailableSensorsFromGIOSApi(){

        List<Station> stationList = stationService.findAllByService(airQualityServiceService.findByName("GIOS"));
        List<SensorGIOSModel> sensorList;

        for(Station station : stationList){

            sensorList = giosApiMapper.getSensorsFromStation(station.getIdApi());
            sensorService.saveAll(sensorList);
        }
    }

}