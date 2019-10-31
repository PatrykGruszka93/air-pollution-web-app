package com.gruszka.airpollutionwebapp.service;

import com.gruszka.airpollutionwebapp.entity.Station;
import com.gruszka.airpollutionwebapp.gios.GIOSApiMapper;
import com.gruszka.airpollutionwebapp.gios.GIOSModelAdapter;
import com.gruszka.airpollutionwebapp.gios.model.StationGIOSModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StationServiceImplTest {

    @Autowired
    StationService stationService;

    @Autowired
    GIOSApiMapper giosApiMapper;

    @Autowired
    GIOSModelAdapter GIOSModelAdapter;

    @Autowired
    AirQualityServiceService airQualityServiceService;

    @Test
    public void shouldSaveOneStationInDB(){
        Station station = new Station();

        station.setId(120);
        station.setService(airQualityServiceService.findByName("GIOS"));
        station.setStationName("Model Station");
        station.setGegrLat(50.2050);
        station.setGegrLon(45.5000);
        station.setCity(null);
        station.setStreetAddress("Model Street");

        stationService.save(station);
    }

    @Test
    public void shouldSaveStationsInDB(){
        List<StationGIOSModel> stationList = giosApiMapper.getAllStations();

        stationService.saveAll(stationList);
    }

}