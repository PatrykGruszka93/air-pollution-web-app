package com.gruszka.airpollutionwebapp.service;

import com.gruszka.airpollutionwebapp.entity.AirQualityService;
import com.gruszka.airpollutionwebapp.entity.Sensor;
import com.gruszka.airpollutionwebapp.entity.Station;
import com.gruszka.airpollutionwebapp.gios.GIOSApiMapper;
import com.gruszka.airpollutionwebapp.gios.model.PollutionDataGIOSModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PollutionDataServiceImplTest {

    @Autowired
    private PollutionDataService pollutionDataService;

    @Autowired
    private SensorService sensorService;

    @Autowired
    private StationService stationService;

    @Autowired
    private AirQualityServiceService airQualityServiceService;

    @Autowired
    private GIOSApiMapper giosApiMapper;

    protected final Logger LOG = Logger.getLogger(getClass().getName());

    @Test
    public void shouldPopulateDataFromGIOSApi(){

        List<Station> stations = stationService.findAllByService(airQualityServiceService.findByName("GIOS"));

        List<Sensor> sensors;
        for(Station station : stations){
            LOG.info("------> Getting data from station: id: " + station.getId() + ", idApi: " + station.getIdApi());
            sensors = sensorService.findAllByStation(station);
            for(Sensor sensor : sensors){
                LOG.info("------> Getting data from sensor: id: " + sensor.getId() + ", idApi: " + sensor.getIdApi());
                PollutionDataGIOSModel pollutionDataModel = giosApiMapper.getDataFromSensor(sensor.getIdApi());
                pollutionDataService.saveAll(pollutionDataModel, sensor);
            }

        }

    }

    @Test
    public void shouldPopulateDataFromOnlyOneSensorFromGIOSApi(){

        Sensor sensor = sensorService.findByIdApiAndStation(225, stationService.findByStationName("Kłodzko - Szkolna"));
        PollutionDataGIOSModel pollutionDataModel = giosApiMapper.getDataFromSensor(sensor.getIdApi());
        pollutionDataService.saveAll(pollutionDataModel, sensor);

    }



}