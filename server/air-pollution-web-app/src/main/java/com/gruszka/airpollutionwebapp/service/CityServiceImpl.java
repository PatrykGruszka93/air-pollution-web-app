package com.gruszka.airpollutionwebapp.service;

import com.gruszka.airpollutionwebapp.dao.CityDao;
import com.gruszka.airpollutionwebapp.entity.City;
import com.gruszka.airpollutionwebapp.gios.GIOSModelAdapter;
import com.gruszka.airpollutionwebapp.gios.model.CityGIOSModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService{

    private CityDao cityDao;
    private GIOSModelAdapter giosModelAdapter;

    @Autowired
    public CityServiceImpl(CityDao cityDao, GIOSModelAdapter giosModelAdapter) {
        this.cityDao = cityDao;
        this.giosModelAdapter = giosModelAdapter;
    }

    @Override
    public void save(CityGIOSModel cityGIOSModel) {
        City city;

        city = giosModelAdapter.getCity(cityGIOSModel);
        save(city);
    }

    @Override
    public void saveAll(List<CityGIOSModel> cities) {

        City city;
        for(CityGIOSModel cityModel : cities){
            city = giosModelAdapter.getCity(cityModel);
            save(city);
        }
    }

    @Override
    public void save(City city) {
        City tmpCity;

        try{
            tmpCity = findByName(city.getName());
            city.setId(tmpCity.getId());
        } catch (RuntimeException e){
            city.setId(-1);
        } finally {
            cityDao.save(city);
        }
    }

    @Override
    public City findByName(String name) {

        Optional<City> result = cityDao.findByName(name);
        City city;

        if(result.isPresent()){
            city = result.get();
        }
        else {
            throw new RuntimeException("Did not find City: " + name);
        }

        return city;
    }
}
