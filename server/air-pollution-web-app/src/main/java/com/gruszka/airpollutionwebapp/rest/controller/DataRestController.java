package com.gruszka.airpollutionwebapp.rest.controller;

import com.gruszka.airpollutionwebapp.entity.PollutionData;
import com.gruszka.airpollutionwebapp.entity.Sensor;
import com.gruszka.airpollutionwebapp.rest.RestApiModelAdapter;
import com.gruszka.airpollutionwebapp.rest.model.PollutionDataRestApiModel;
import com.gruszka.airpollutionwebapp.service.PollutionDataService;
import com.gruszka.airpollutionwebapp.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/data")
public class DataRestController {

    private SensorService sensorService;
    private PollutionDataService pollutionDataService;
    private RestApiModelAdapter modelAdapter;

    @Autowired
    public DataRestController(SensorService sensorService, PollutionDataService pollutionDataService, RestApiModelAdapter modelAdapter) {
        this.sensorService = sensorService;
        this.pollutionDataService = pollutionDataService;
        this.modelAdapter = modelAdapter;
    }

    @GetMapping("/recent/station/{stationId}")
    public List<PollutionDataRestApiModel> getRecentDataFromStation(@PathVariable("stationId") Integer stationId){

        List<PollutionDataRestApiModel> dataList = new ArrayList<>();
        PollutionData pollutionData;
        PollutionDataRestApiModel dataModel;

        List<Sensor> sensors = sensorService.findAllByStationId(stationId);

        for(Sensor sensor : sensors){
            pollutionData = pollutionDataService.findMostRecentDataFromSensor(sensor);
            dataModel = modelAdapter.getPollutionDataRestApiModel(pollutionData);
            dataList.add(dataModel);
        }
        return dataList;
    }

}
