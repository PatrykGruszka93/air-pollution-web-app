package com.gruszka.airpollutionwebapp.service;

import com.gruszka.airpollutionwebapp.entity.Index;
import com.gruszka.airpollutionwebapp.entity.PollutionData;
import com.gruszka.airpollutionwebapp.entity.Sensor;
import com.gruszka.airpollutionwebapp.entity.Station;
import com.gruszka.airpollutionwebapp.gios.GIOSApiMapper;
import com.gruszka.airpollutionwebapp.gios.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class MainAppServiceImpl implements MainAppService {

    private AirQualityServiceService airQualityServiceService;
    private CityService cityService;
    private CommuneService communeService;
    private PollutionDataService pollutionDataService;
    private PollutionDataHistoryService pollutionDataHistoryService;
    private SensorService sensorService;
    private StationService stationService;
    private GIOSApiMapper giosApiMapper;
    private AirQualityIndexService airQualityIndexService;

    public static final int REFRESH_RATE = 3000000; //30 min

    protected final Logger LOG = Logger.getLogger(getClass().getName());

    @Autowired
    public MainAppServiceImpl(AirQualityServiceService airQualityServiceService, CityService cityService, CommuneService communeService, PollutionDataService pollutionDataService, PollutionDataHistoryService pollutionDataHistoryService, SensorService sensorService, StationService stationService, GIOSApiMapper giosApiMapper, AirQualityIndexService airQualityIndexService) {
        this.airQualityServiceService = airQualityServiceService;
        this.cityService = cityService;
        this.communeService = communeService;
        this.pollutionDataService = pollutionDataService;
        this.pollutionDataHistoryService = pollutionDataHistoryService;
        this.sensorService = sensorService;
        this.stationService = stationService;
        this.giosApiMapper = giosApiMapper;
        this.airQualityIndexService = airQualityIndexService;
    }

    @Override
    //@Scheduled(fixedRate = REFRESH_RATE)
    public void populateDBFromGIOSData() {

        LOG.info("Getting data from GIOS " + new Date());

        getAllCommuneFromGIOS();
        getAllCitiesFromGIOS();
        getAllStationsFromGIOS();
        getAllSensorsFromGIOS();
        getAllPollutionDataFromGIOS();
        refreshAirQualityIndicesForGIOSStations();


        LOG.info("Getting data from GIOS has been finished " + new Date());
    }

    @Override
    @Scheduled(cron = "0 0 22 * * *")
    public void transferPollutionDataToHistoryTable() {
        pollutionDataHistoryService.transferDataIntoDataHistory();
        pollutionDataService.deleteOldData();
    }


    private void getAllCommuneFromGIOS() {
        List<StationGIOSModel> stationList = giosApiMapper.getAllStations();
        CommuneGIOSModel communeModel;
        LOG.info("Getting communes from GIOS");

        for(StationGIOSModel stationGIOSModel : stationList){
            try{
                communeModel = stationGIOSModel.getCity().getCommune();
                communeService.save(communeModel);
            }catch (NullPointerException e){
                continue;
            }
        }
    }

    private void getAllCitiesFromGIOS() {
        List<StationGIOSModel> stationList = giosApiMapper.getAllStations();
        CityGIOSModel cityModel;
        LOG.info("Getting cities from GIOS");

        for(StationGIOSModel stationGIOSModel : stationList){
            try{
                cityModel = stationGIOSModel.getCity();
                cityService.save(cityModel);
            }catch (NullPointerException e){
                continue;
            }
        }
    }

    private void getAllStationsFromGIOS() {
        LOG.info("Saving stations from GIOS");
        List<StationGIOSModel> stationList = giosApiMapper.getAllStations();
        stationService.saveAll(stationList);

    }

    private void getAllSensorsFromGIOS() {
        LOG.info("Saving sensors from GIOS");
        List<Station> stationList = stationService.findAllByService(airQualityServiceService.findByName("GIOS"));
        List<SensorGIOSModel> sensorList;

        for(Station station : stationList){
            sensorList = giosApiMapper.getSensorsFromStation(station.getIdApi());
            sensorService.saveAll(sensorList);
        }
    }

    private void getAllPollutionDataFromGIOS() {
        LOG.info("Saving pollution data from GIOS");
        List<Station> stations = stationService.findAllByService(airQualityServiceService.findByName("GIOS"));
        List<Sensor> sensors;
        List<Sensor> failedSensors = new ArrayList<>();
        for(Station station : stations){
            LOG.info("------> Getting data from station: id: " + station.getId() + ", idApi: " + station.getIdApi());
            sensors = sensorService.findAllByStation(station);
            for(Sensor sensor : sensors){
                LOG.info("------> Getting data from sensor: id: " + sensor.getId() + ", idApi: " + sensor.getIdApi());
                PollutionDataGIOSModel pollutionDataModel;
                try {
                    pollutionDataModel = giosApiMapper.getDataFromSensor(sensor.getIdApi());
                } catch (IOException e) {
                    failedSensors.add(sensor);
                    continue;
                }
                pollutionDataService.saveAll(pollutionDataModel, sensor);
            }
        }
        for(Sensor failedSensor : failedSensors){
            LOG.log(Level.INFO, "Failed to get data from (Api Id): " + failedSensor.getIdApi());
        }
    }

    private void refreshAirQualityIndicesForGIOSStations() {
        LOG.info("Refreshing air quality indices for GIOS Stations");
        List<Station> stations = stationService.findAllByService(airQualityServiceService.findByName("GIOS"));
        PollutionData dataWithHighestIndex = null;
        for(Station station : stations){
            dataWithHighestIndex = getDataFromMostPollutedAirSensorOfStation(station);
            airQualityIndexService.saveIndexFromMostPollutedSensor(dataWithHighestIndex);
        }
    }

    private PollutionData getDataFromMostPollutedAirSensorOfStation(Station station) {

        PollutionData dataWithHighestIndex = prepareTmpData();

        List<PollutionData> listOfRecentData = getListOfMostRecentDataFromOneStation(station);

        for(PollutionData pollutionData : listOfRecentData){
            if(pollutionData.getIndex().getId()> dataWithHighestIndex.getIndex().getId()){
                dataWithHighestIndex = pollutionData;
            }
        }
        return dataWithHighestIndex;
    }

    private PollutionData prepareTmpData(){
        PollutionData tmpPollutionData  = new PollutionData();
        Index tmpIndex = new Index();
        tmpIndex.setId(0);
        tmpPollutionData.setIndex(tmpIndex);
        return tmpPollutionData;
    }

    private List<PollutionData> getListOfMostRecentDataFromOneStation(Station station) {
        PollutionData pollutionData;
        List<PollutionData> allData = new LinkedList<>();

        List<Sensor> sensors = sensorService.findAllByStation(station);
        for(Sensor sensor : sensors) {
            try{
                pollutionData = pollutionDataService.findMostRecentIndexFromSensor(sensor);

            } catch (RuntimeException e){
                LOG.info(e.getMessage());
                continue;
            }
            allData.add(pollutionData);

        }
        return allData;
    }
}
