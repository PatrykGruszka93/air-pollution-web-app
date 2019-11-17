package com.gruszka.airpollutionwebapp.rest.controller;

import com.gruszka.airpollutionwebapp.entity.AirQualityIndex;
import com.gruszka.airpollutionwebapp.entity.AirQualityService;
import com.gruszka.airpollutionwebapp.entity.Station;
import com.gruszka.airpollutionwebapp.rest.RestApiModelAdapter;
import com.gruszka.airpollutionwebapp.rest.model.StationRestApiModel;
import com.gruszka.airpollutionwebapp.service.AirQualityIndexService;
import com.gruszka.airpollutionwebapp.service.AirQualityServiceService;
import com.gruszka.airpollutionwebapp.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/stations")
public class StationRestController {


    private StationService stationService;

    private AirQualityIndexService aqiService;

    private AirQualityServiceService aqsService;

    private RestApiModelAdapter restApiModelAdapter;

    @Autowired
    public StationRestController(StationService stationService, AirQualityIndexService aqiService,
                                 AirQualityServiceService aqsService, RestApiModelAdapter restApiModelAdapter) {
        this.stationService = stationService;
        this.aqiService = aqiService;
        this.aqsService = aqsService;
        this.restApiModelAdapter = restApiModelAdapter;
    }

    @GetMapping("")
    public List<StationRestApiModel> getStations() {


        AirQualityService aqs = aqsService.findByName("GIOS");

        List<StationRestApiModel> stationsModel = new ArrayList<>();
        StationRestApiModel stationModel;
        AirQualityIndex index;
        List<Station> stations = stationService.findStationsBasicDetailsByService(aqs);

        for(Station station : stations){
            index = aqiService.findMostCurrentIndexForStation(station);
            stationModel = restApiModelAdapter.getStationRestApiModel(station, index);
            stationsModel.add(stationModel);
        }


        return stationsModel;

    }







}
