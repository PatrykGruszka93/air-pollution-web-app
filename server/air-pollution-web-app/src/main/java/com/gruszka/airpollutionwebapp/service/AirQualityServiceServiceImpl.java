package com.gruszka.airpollutionwebapp.service;

import com.gruszka.airpollutionwebapp.dao.AirQualityServiceDao;
import com.gruszka.airpollutionwebapp.entity.AirQualityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirQualityServiceServiceImpl implements AirQualityServiceService {

    private AirQualityServiceDao airQualityServiceDao;

    @Autowired
    public AirQualityServiceServiceImpl(AirQualityServiceDao airQualityServiceDao) {
        this.airQualityServiceDao = airQualityServiceDao;
    }

    @Override
    public List<AirQualityService> findAll() {
        return airQualityServiceDao.findAll();
    }

    @Override
    public AirQualityService findByName(String name) {

        Optional<AirQualityService> result = airQualityServiceDao.findByName(name);
        AirQualityService airQualityService = null;

        if(result.isPresent()){
            airQualityService = result.get();
        }
        else{
            throw new RuntimeException("Did not find Service: " + name);
        }
        return airQualityService;
    }
}
