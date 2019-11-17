package com.gruszka.airpollutionwebapp.service;

import com.gruszka.airpollutionwebapp.dao.AirQualityIndexDao;
import com.gruszka.airpollutionwebapp.entity.AirQualityIndex;
import com.gruszka.airpollutionwebapp.entity.PollutionData;
import com.gruszka.airpollutionwebapp.entity.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class AirQualityIndexServiceImpl implements AirQualityIndexService {

    private AirQualityIndexDao airQualityIndexDao;
    private PollutionDataService pollutionDataService;

    protected final Logger LOG = Logger.getLogger(getClass().getName());


    @Autowired
    public AirQualityIndexServiceImpl(AirQualityIndexDao airQualityIndexDao, PollutionDataService pollutionDataService) {
        this.airQualityIndexDao = airQualityIndexDao;
        this.pollutionDataService = pollutionDataService;
    }

    @Override
    public void save(AirQualityIndex airQualityIndex) {

        LOG.info("Saving Air Quality Index for station " + airQualityIndex.getStation().getId());
        airQualityIndexDao.save(airQualityIndex);
    }

    @Override
    public AirQualityIndex findById(Long id) {
        return null;
    }

    @Override
    public void saveIndexFromMostPollutedSensor(PollutionData mostPollutedSensor) {
        AirQualityIndex airQualityIndex = new AirQualityIndex();
        AirQualityIndex tmpAirQualityIndex;

        airQualityIndex.setStation(mostPollutedSensor.getSensor().getStation());
        airQualityIndex.setCalcDate(mostPollutedSensor.getDate());
        airQualityIndex.setIndex(mostPollutedSensor.getIndex());

        try {
            tmpAirQualityIndex = findByCalcDateAndStation(mostPollutedSensor.getDate(), mostPollutedSensor.getSensor().getStation());
            airQualityIndex.setId(tmpAirQualityIndex.getId());

        } catch (RuntimeException e){
            airQualityIndex.setId(-1);
        } finally {
            save(airQualityIndex);
        }

    }

    @Override
    public AirQualityIndex findByCalcDateAndStation(Date calcDate, Station station) {
        Optional<AirQualityIndex> result = airQualityIndexDao.findByCalcDateAndStation(calcDate, station);

        AirQualityIndex airQualityIndex;

        if(result.isPresent()){
            airQualityIndex = result.get();
        } else {
            throw new RuntimeException("Did not find Air Quality Index by Station: " + station.getId() + " date: " + calcDate.toString());
        }
        return airQualityIndex;
    }
}
