package com.gruszka.airpollutionwebapp.service;

import com.gruszka.airpollutionwebapp.dao.AirQualityServiceDao;
import com.gruszka.airpollutionwebapp.entity.AirQualityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
