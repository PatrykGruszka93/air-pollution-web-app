package com.gruszka.airpollutionwebapp.rest.controller;

import com.gruszka.airpollutionwebapp.entity.*;
import com.gruszka.airpollutionwebapp.rest.RestApiModelAdapter;
import com.gruszka.airpollutionwebapp.rest.model.ParameterRestApiModel;
import com.gruszka.airpollutionwebapp.rest.model.PollutionDataHistoryRestApiModel;
import com.gruszka.airpollutionwebapp.rest.model.PollutionDataRestApiModel;
import com.gruszka.airpollutionwebapp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/data")
public class DataRestController {

    private SensorService sensorService;
    private PollutionDataService pollutionDataService;
    private PollutionDataHistoryService pollutionDataHistoryService;
    private CityService cityService;
    private ParameterService parameterService;
    private RestApiModelAdapter modelAdapter;

    @Autowired
    public DataRestController(SensorService sensorService, PollutionDataService pollutionDataService,
                              PollutionDataHistoryService pollutionDataHistoryService, CityService cityService,
                              ParameterService parameterService, RestApiModelAdapter modelAdapter) {
        this.sensorService = sensorService;
        this.pollutionDataService = pollutionDataService;
        this.pollutionDataHistoryService = pollutionDataHistoryService;
        this.cityService = cityService;
        this.parameterService = parameterService;
        this.modelAdapter = modelAdapter;
    }

    @GetMapping("/recent/station/{stationId}")
    public List<PollutionDataRestApiModel> getRecentDataFromStation(@PathVariable("stationId") Integer stationId){

        List<PollutionDataRestApiModel> dataList = new ArrayList<>();
        PollutionData pollutionData;
        PollutionDataRestApiModel dataModel;

        List<Sensor> sensors = sensorService.findAllByStationId(stationId);

        for(Sensor sensor : sensors){
            try{
                pollutionData = pollutionDataService.findMostRecentDataFromSensor(sensor);
                dataModel = modelAdapter.getPollutionDataRestApiModel(pollutionData);
                dataList.add(dataModel);
            } catch (RuntimeException e){
                continue;
            }

        }
        return dataList;
    }

    @GetMapping("/hist/{cityId}/{year}/{month}/{parameterId}")
    public List<PollutionDataHistoryRestApiModel> getHistoryData(
            @PathVariable("cityId") Integer cityId,
            @PathVariable("year") Integer year,
            @PathVariable("month") Integer month,
            @PathVariable("parameterId") Integer parameterId) {

        List<PollutionDataHistoryRestApiModel> dataModelList = new ArrayList<>();
        PollutionDataHistoryRestApiModel dataModel;

        City city = cityService.findById(cityId);
        Parameter parameter = parameterService.findById(parameterId);
        Date date = null;
        StringBuilder sb = new StringBuilder();
        sb.append(year);
        sb.append("-");
        sb.append(month + "-" + "02");

        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(sb.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("Data: " + date.toString());
        List<PollutionDataHistory> pollutionList =
                pollutionDataHistoryService.findAvgDataByCityAndDateAndParameter(city, date, parameter, "'%Y-%m'");

        for (PollutionDataHistory data : pollutionList) {
            dataModel = modelAdapter.getPollutionDataHistoryRestApiModel(data);
            dataModelList.add(dataModel);
        }
        return dataModelList;
    }

    @GetMapping("/hist/{cityId}")
    public List<ParameterRestApiModel> getParametersForCity(@PathVariable Integer cityId) {

        if (cityId.equals(null) || cityId.equals(0)) {
            return null;
        }

        List<Parameter> parameterList = parameterService.findDistinctByCityId(cityId);
        List<ParameterRestApiModel> parameterRestList = new ArrayList<>();
        for (Parameter parameter : parameterList) {
            parameterRestList.add(modelAdapter.getParameterRestApiModel(parameter));
        }
        return parameterRestList;
    }
}
