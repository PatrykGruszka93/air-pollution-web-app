package com.gruszka.airpollutionwebapp.gios;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gruszka.airpollutionwebapp.gios.model.PollutionData;
import com.gruszka.airpollutionwebapp.gios.model.Sensor;
import com.gruszka.airpollutionwebapp.gios.model.Station;

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

    public List<Station> getAllStations(){

        List<Station> stations = null;
        final String stringUrl = "http://api.gios.gov.pl/pjp-api/rest/station/findAll";
        URL stationsUrl = getUrlInstance(stringUrl);
        try {
            stations = Arrays.asList(mapper.readValue(stationsUrl, Station[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stations;
    }

    public List<Sensor> getSensorsFromStation(int stationId){
        List<Sensor> sensors = null;
        String stringUrl = prepareUrl("http://api.gios.gov.pl/pjp-api/rest/station/sensors/", stationId);

        URL sensorsURL = getUrlInstance(stringUrl);

        try {
            sensors = Arrays.asList(mapper.readValue(sensorsURL, Sensor[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sensors;
    }

    public PollutionData getDataFromSensor(int sensorId){
        PollutionData pollutionData = null;
        String stringUrl = prepareUrl("http://api.gios.gov.pl/pjp-api/rest/data/getData/", sensorId);

        URL dataURL = getUrlInstance(stringUrl);

        try {
            pollutionData = mapper.readValue(dataURL, PollutionData.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pollutionData;
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
