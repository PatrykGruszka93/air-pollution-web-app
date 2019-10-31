package com.gruszka.airpollutionwebapp.service;

import com.gruszka.airpollutionwebapp.dao.StationDao;
import com.gruszka.airpollutionwebapp.entity.Station;
import com.gruszka.airpollutionwebapp.gios.GIOSModelAdapter;
import com.gruszka.airpollutionwebapp.gios.model.StationGIOSModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StationServiceImpl implements StationService{

    private StationDao stationDao;
    private GIOSModelAdapter giosModelAdapter;

    @Autowired
    public StationServiceImpl(StationDao stationDao, GIOSModelAdapter GIOSModelAdapter) {
        this.stationDao = stationDao;
        this.giosModelAdapter = GIOSModelAdapter;
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

}
