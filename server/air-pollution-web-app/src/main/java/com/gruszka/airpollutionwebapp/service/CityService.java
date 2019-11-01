package com.gruszka.airpollutionwebapp.service;

import com.gruszka.airpollutionwebapp.entity.City;
import com.gruszka.airpollutionwebapp.gios.model.CityGIOSModel;

import java.util.List;

public interface CityService {

    void save(City city);

    void save(CityGIOSModel cityGIOSModel);

    void saveAll(List<CityGIOSModel> cities);

    City findByName(String name);



}
