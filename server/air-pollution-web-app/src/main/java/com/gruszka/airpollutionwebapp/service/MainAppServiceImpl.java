package com.gruszka.airpollutionwebapp.service;

import com.gruszka.airpollutionwebapp.entity.Sensor;
import com.gruszka.airpollutionwebapp.entity.Station;
import com.gruszka.airpollutionwebapp.gios.GIOSApiMapper;
import com.gruszka.airpollutionwebapp.gios.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Service
public class MainAppServiceImpl implements MainAppService {

    private AirQualityServiceService airQualityServiceService;
    private CityService cityService;
    private CommuneService communeService;
    private PollutionDataService pollutionDataService;
    private SensorService sensorService;
    private StationService stationService;
    private GIOSApiMapper giosApiMapper;

    public static final int REFRESH_RATE = 3000000; //30 min

    protected final Logger LOG = Logger.getLogger(getClass().getName());

    @Autowired
    public MainAppServiceImpl(AirQualityServiceService airQualityServiceService,
                              CityService cityService, CommuneService communeService,
                              PollutionDataService pollutionDataService, SensorService sensorService,
                              StationService stationService, GIOSApiMapper giosApiMapper) {
        this.airQualityServiceService = airQualityServiceService;
        this.cityService = cityService;
        this.communeService = communeService;
        this.pollutionDataService = pollutionDataService;
        this.sensorService = sensorService;
        this.stationService = stationService;
        this.giosApiMapper = giosApiMapper;
    }

    @Override
    @Scheduled(fixedRate = REFRESH_RATE)
    public void populateDBFromGIOSData() {

        LOG.info("Getting data from GIOS " + new Date());

        getAllCommuneFromGIOS();
        getAllCitiesFromGIOS();
        getAllStationsFromGIOS();
        getAllSensorsFromGIOS();
        getAllPollutionDataFromGIOS();

        LOG.info("Getting data from GIOS has been finished " + new Date());
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

}
