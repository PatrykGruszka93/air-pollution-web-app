package com.gruszka.airpollutionwebapp.gios;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gruszka.airpollutionwebapp.gios.model.PollutionDataGIOSModel;
import com.gruszka.airpollutionwebapp.gios.model.SensorGIOSModel;
import com.gruszka.airpollutionwebapp.gios.model.StationGIOSModel;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class GIOSApiMapper {

    private ObjectMapper mapper;

    public GIOSApiMapper() {
        this.mapper = new ObjectMapper();
    }

    public List<StationGIOSModel> getAllStations(){

        List<StationGIOSModel> stationGIOSModels = null;
        final String stringUrl = "http://api.gios.gov.pl/pjp-api/rest/station/findAll";
        URL stationsUrl = getUrlInstance(stringUrl);
        try {
            stationGIOSModels = Arrays.asList(mapper.readValue(stationsUrl, StationGIOSModel[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stationGIOSModels;
    }

    public List<SensorGIOSModel> getSensorsFromStation(int stationId){
        List<SensorGIOSModel> sensorGIOSModels = null;
        String stringUrl = prepareUrl("http://api.gios.gov.pl/pjp-api/rest/station/sensors/", stationId);

        URL sensorsURL = getUrlInstance(stringUrl);

        try {
            sensorGIOSModels = Arrays.asList(mapper.readValue(sensorsURL, SensorGIOSModel[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sensorGIOSModels;
    }

    public PollutionDataGIOSModel getDataFromSensor(int sensorId){
        PollutionDataGIOSModel pollutionDataGIOSModel = null;
        String stringUrl = prepareUrl("http://api.gios.gov.pl/pjp-api/rest/data/getData/", sensorId);

        URL dataURL = getUrlInstance(stringUrl);

        try {
            pollutionDataGIOSModel = mapper.readValue(dataURL, PollutionDataGIOSModel.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pollutionDataGIOSModel;
    }

    private URL getUrlInstance(String stringUrl) {
        URL stationsUrl = null;
        try {
            stationsUrl = new URL(stringUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return stationsUrl;
    }

    private String prepareUrl(String apiUrl, int id) {
        StringBuilder stringUrl = new StringBuilder(apiUrl);
        stringUrl.append(id);
        return stringUrl.toString();
    }

}
