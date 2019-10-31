package com.gruszka.airpollutionwebapp.gios;

import com.gruszka.airpollutionwebapp.entity.AirQualityService;
import com.gruszka.airpollutionwebapp.entity.City;
import com.gruszka.airpollutionwebapp.entity.Station;
import com.gruszka.airpollutionwebapp.gios.model.CityGIOSModel;
import com.gruszka.airpollutionwebapp.gios.model.StationGIOSModel;
import com.gruszka.airpollutionwebapp.service.AirQualityServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GIOSModelAdapter {


    private AirQualityServiceService airQualityServiceService;



    @Autowired
    public GIOSModelAdapter(AirQualityServiceService airQualityServiceService) {
        this.airQualityServiceService = airQualityServiceService;
    }

    public Station getStation(StationGIOSModel model){

        Station station = new Station();

        station.setId(model.getId());
        station.setService(airQualityServiceService.findByName("GIOS"));
        station.setStationName(model.getStationName());
        station.setGegrLat(model.getGegrLat());
        station.setGegrLon(model.getGegrLon());
        //station.setCity();
        station.setStreetAddress(model.getAddressStreet());

        return station;
    }

    public City getCity(CityGIOSModel model){

        City city = new City();

        city.setId(model.getId());
        city.setName(model.getName());
        //city.setCommune(model.getCommune());


        return city;

    }

}
