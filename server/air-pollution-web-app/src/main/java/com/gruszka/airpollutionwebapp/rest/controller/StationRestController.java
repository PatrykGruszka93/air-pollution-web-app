package com.gruszka.airpollutionwebapp.rest.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.gruszka.airpollutionwebapp.entity.AirQualityIndex;
import com.gruszka.airpollutionwebapp.entity.AirQualityService;
import com.gruszka.airpollutionwebapp.entity.Station;
import com.gruszka.airpollutionwebapp.rest.GeoJsonAdapter;
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
    private GeoJsonAdapter geoJsonAdapter;

    @Autowired
    public StationRestController(StationService stationService, AirQualityIndexService aqiService,
                                 AirQualityServiceService aqsService, RestApiModelAdapter restApiModelAdapter,
                                 GeoJsonAdapter geoJsonAdapter) {
        this.stationService = stationService;
        this.aqiService = aqiService;
        this.aqsService = aqsService;
        this.restApiModelAdapter = restApiModelAdapter;
        this.geoJsonAdapter = geoJsonAdapter;
    }

    @GetMapping("/json")
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

    @GetMapping("/geoJson")
    public JsonNode getStationsGeoJson(){
        AirQualityService aqs = aqsService.findByName("GIOS");
        List<Station> stations = stationService.findStationsBasicDetailsByService(aqs);

        JsonNode json = geoJsonAdapter.getGeoJsonOfStations(stations);
        return json;

    }


}
