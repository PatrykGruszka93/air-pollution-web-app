package com.gruszka.airpollutionwebapp.rest.controller;

import com.gruszka.airpollutionwebapp.entity.City;
import com.gruszka.airpollutionwebapp.rest.RestApiModelAdapter;
import com.gruszka.airpollutionwebapp.rest.model.CityRestApiModel;
import com.gruszka.airpollutionwebapp.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "api/cities")
public class CityRestController {

    private CityService cityService;
    private RestApiModelAdapter modelAdapter;

    @Autowired
    public CityRestController(CityService cityService, RestApiModelAdapter modelAdapter) {
        this.cityService = cityService;
        this.modelAdapter = modelAdapter;
    }

    @GetMapping(value = "")
    public List<CityRestApiModel> getCities() {
        List<CityRestApiModel> citiesRestModel = new ArrayList<>();
        List<City> cities = cityService.findAllOrderByName();
        for (City city : cities) {
            citiesRestModel.add(modelAdapter.getCityRestApiModel(city));
        }
        return citiesRestModel;
    }

}
