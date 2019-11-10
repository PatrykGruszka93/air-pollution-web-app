package com.gruszka.airpollutionwebapp.gios;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gruszka.airpollutionwebapp.gios.model.PollutionDataGIOSModel;
import com.gruszka.airpollutionwebapp.gios.model.SensorGIOSModel;
import com.gruszka.airpollutionwebapp.gios.model.StationGIOSModel;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Component
public class GIOSApiMapper {

    private ObjectMapper mapper;

    protected final Logger LOG = Logger.getLogger(getClass().getName());

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

    public PollutionDataGIOSModel getDataFromSensor(int sensorId) throws IOException{
        PollutionDataGIOSModel pollutionDataGIOSModel = null;
        String stringUrl = prepareUrl("http://api.gios.gov.pl/pjp-api/rest/data/getData/", sensorId);
        URLConnection urlConn ;
        URL dataURL = getUrlInstance(stringUrl);

        try{
            urlConn = dataURL.openConnection();
            urlConn.setConnectTimeout(5000);
            urlConn.setReadTimeout(5000);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        try(InputStream is = urlConn.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            LOG.info("Getting data from: " + stringUrl);


            pollutionDataGIOSModel = mapper.readValue(br, PollutionDataGIOSModel.class);

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
