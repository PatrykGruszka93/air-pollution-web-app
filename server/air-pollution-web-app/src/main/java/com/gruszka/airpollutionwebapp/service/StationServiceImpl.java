package com.gruszka.airpollutionwebapp.service;

import com.gruszka.airpollutionwebapp.dao.StationDao;
import com.gruszka.airpollutionwebapp.entity.AirQualityIndex;
import com.gruszka.airpollutionwebapp.entity.AirQualityService;
import com.gruszka.airpollutionwebapp.entity.Station;
import com.gruszka.airpollutionwebapp.gios.GIOSModelAdapter;
import com.gruszka.airpollutionwebapp.gios.model.StationGIOSModel;
import com.gruszka.airpollutionwebapp.rest.RestApiModelAdapter;
import com.gruszka.airpollutionwebapp.rest.model.StationRestApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class StationServiceImpl implements StationService{

    private StationDao stationDao;
    private AirQualityIndexService aqiService;
    private GIOSModelAdapter giosModelAdapter;
    private RestApiModelAdapter restApiModelAdapter;

    protected final Logger LOG = Logger.getLogger(getClass().getName());

    @Autowired
    public StationServiceImpl(StationDao stationDao, AirQualityIndexService aqiService,
                              GIOSModelAdapter GIOSModelAdapter, RestApiModelAdapter restApiModelAdapter) {
        this.stationDao = stationDao;
        this.aqiService = aqiService;
        this.giosModelAdapter = GIOSModelAdapter;
        this.restApiModelAdapter = restApiModelAdapter;
    }

    @Override
    public List<Station> findAll() {
        return stationDao.findAll();
    }

    @Override
    public void save(Station station) {
        Station tmpStation;
        try{
            tmpStation = findByStationName(station.getStationName());
            station.setId(tmpStation.getId());
        } catch (RuntimeException e){
            station.setId(-1);
        }
        finally {
            stationDao.save(station);
            LOG.info("Station saved/updated: " + station.getStationName());
        }
    }

    @Override
    public void saveAll(List<StationGIOSModel> stations) {

        Station station;
        for(StationGIOSModel stationModel: stations){
            station = giosModelAdapter.getStation(stationModel);
            save(station);
        }
    }

    @Override
    public Station findByStationName(String stationName) throws RuntimeException{

        Optional<Station> result = stationDao.findByStationName(stationName);
        Station station;
        if(result.isPresent()){
            station = result.get();
        }
        else{
            throw new RuntimeException("Did not find Station: " + stationName);
        }
        return station;
    }

    @Override
    public Station findByIdApiAndService(int idApi, AirQualityService service) {

        Optional<Station> result = stationDao.findByIdApiAndService(idApi, service);
        Station station;

        if(result.isPresent()){
            station = result.get();
        } else {
            throw new RuntimeException("Did not find Station: " + idApi);
        }
        return station;
    }

    @Override
    public List<Station> findAllByService(AirQualityService service) {
        return stationDao.findAllByService(service);
    }

    @Override
    public List<Station> findStationsBasicDetailsByService(AirQualityService service) {

        List<Station> stations = stationDao.findStationsBasicDetailsByService(service);

        return stations;
    }

}
