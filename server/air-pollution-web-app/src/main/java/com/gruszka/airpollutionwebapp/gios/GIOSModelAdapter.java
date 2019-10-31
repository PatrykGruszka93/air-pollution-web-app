package com.gruszka.airpollutionwebapp.gios;

import com.gruszka.airpollutionwebapp.entity.AirQualityService;
import com.gruszka.airpollutionwebapp.entity.City;
import com.gruszka.airpollutionwebapp.entity.Commune;
import com.gruszka.airpollutionwebapp.entity.Station;
import com.gruszka.airpollutionwebapp.gios.model.CityGIOSModel;
import com.gruszka.airpollutionwebapp.gios.model.CommuneGIOSModel;
import com.gruszka.airpollutionwebapp.gios.model.StationGIOSModel;
import com.gruszka.airpollutionwebapp.service.AirQualityServiceService;
import com.gruszka.airpollutionwebapp.service.CityService;
import com.gruszka.airpollutionwebapp.service.CommuneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GIOSModelAdapter {

    private AirQualityServiceService airQualityServiceService;
    private CityService cityService;
    private CommuneService communeService;


    public Station getStation(StationGIOSModel model){

        Station station = new Station();

        station.setId(model.getId());
        station.setIdApi(model.getId());
        station.setService(airQualityServiceService.findByName("GIOS"));
        station.setStationName(model.getStationName());
        station.setGegrLat(model.getGegrLat());
        station.setGegrLon(model.getGegrLon());
        try{
            station.setCity(cityService.findByName(model.getCity().getName()));
        }catch (NullPointerException e){
            station.setCity(null);
        }
        station.setStreetAddress(model.getAddressStreet());

        return station;
    }

    public City getCity(CityGIOSModel model){

        City city = new City();

        city.setId(model.getId());
        city.setName(model.getName());
        try {
            city.setCommune(communeService.findByCommuneName(model.getCommune().getCommuneName()));
        } catch (NullPointerException e){
            city.setCommune(null);
        }
        return city;

    }

    public Commune getCommune(CommuneGIOSModel model) {

        Commune commune = new Commune();

        commune.setId(model.getId());
        commune.setCommuneName(model.getCommuneName());
        commune.setDistrictName(model.getDistrictName());
        commune.setProvinceName(model.getProvinceName());

        return commune;

    }

    @Autowired
    public void setAirQualityServiceService(AirQualityServiceService airQualityServiceService) {
        this.airQualityServiceService = airQualityServiceService;
    }

    @Autowired
    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    @Autowired
    public void setCommuneService(CommuneService communeService) {
        this.communeService = communeService;
    }
}
