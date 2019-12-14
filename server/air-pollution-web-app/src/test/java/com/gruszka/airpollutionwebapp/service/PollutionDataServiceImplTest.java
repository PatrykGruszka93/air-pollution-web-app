package com.gruszka.airpollutionwebapp.service;

import com.gruszka.airpollutionwebapp.entity.*;
import com.gruszka.airpollutionwebapp.gios.GIOSApiMapper;
import com.gruszka.airpollutionwebapp.gios.model.PollutionDataGIOSModel;
import org.apache.tomcat.jni.Poll;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
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
    private GIOSApiMapper giosApiMapper;

    protected final Logger LOG = Logger.getLogger(getClass().getName());

    @Test
    public void shouldPopulateDataFromOnlyOneSensorFromGIOSApi(){

        Sensor sensor = sensorService.findByIdApiAndStation(225, stationService.findByStationName("Kłodzko - Szkolna"));
        PollutionDataGIOSModel pollutionDataModel = null;
        try {
            pollutionDataModel = giosApiMapper.getDataFromSensor(sensor.getIdApi());
        } catch (IOException e) {
            e.printStackTrace();
        }
        pollutionDataService.saveAll(pollutionDataModel, sensor);
    }

    @Test
    public void findOneInstanceOfAnIndexThatIsMostRecent(){

        Sensor sensor = sensorService.findByIdApiAndStation(225, stationService.findByStationName("Kłodzko - Szkolna"));

        PollutionData pollutionData = pollutionDataService.findMostRecentIndexFromSensor(sensor);

        LOG.info("Got: " + pollutionData.getIndex().getName() + ", date: " + pollutionData.getDate());
    }


    @Test
    public void findDataToTransferIntoHistoryTable(){

        List<PollutionDataHistory> dataList = pollutionDataService.findDataForTransferIntoHistoryTable();

        for(PollutionDataHistory data : dataList){
            LOG.info("Got data: " + data.getDate() + ", Value: " + data.getValue() + ", Sensor_id: " + data.getSensor().getId());
        }
        LOG.info("List length: " + dataList.size());

    }




}