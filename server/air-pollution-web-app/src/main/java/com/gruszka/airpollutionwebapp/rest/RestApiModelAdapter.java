package com.gruszka.airpollutionwebapp.rest;

import com.gruszka.airpollutionwebapp.entity.*;
import com.gruszka.airpollutionwebapp.rest.model.*;
import org.springframework.stereotype.Component;

@Component
public class RestApiModelAdapter {

    public StationRestApiModel getStationRestApiModel(Station station, AirQualityIndex aqi){
        StationRestApiModel model = new StationRestApiModel();

        model.setId(station.getId());
        model.setIdApi(station.getIdApi());
        model.setStationName(station.getStationName());
        model.setGegrLat(station.getGegrLat());
        model.setGegrLon(station.getGegrLon());
        model.setStreetAddress(station.getStreetAddress());
        model.setService(getServiceRestApiModel(station.getService()));
        model.setCity(getCityRestApiModel(station.getCity()));
        model.setAqi(getAqiRestApiModel(aqi));

        return model;
    }

    public ServiceRestApiModel getServiceRestApiModel(AirQualityService aqs){
        ServiceRestApiModel model = new ServiceRestApiModel();

        model.setId(aqs.getId());
        model.setName(aqs.getName());

        return model;
    }

    public CityRestApiModel getCityRestApiModel(City city){
        CityRestApiModel model = new CityRestApiModel();

        model.setId(city.getId());
        model.setName(city.getName());
        model.setCommune(getCommuneRestApiModel(city.getCommune()));

        return model;
    }

    public CommuneRestApiModel getCommuneRestApiModel(Commune commune){
        CommuneRestApiModel model = new CommuneRestApiModel();

        model.setId(commune.getId());
        model.setCommuneName(commune.getCommuneName());
        model.setDistrictName(commune.getDistrictName());
        model.setProvinceName(commune.getDistrictName());

        return model;
    }

    public AirQualityIndexRestApiModel getAqiRestApiModel(AirQualityIndex aqi){
        AirQualityIndexRestApiModel model = new AirQualityIndexRestApiModel();

        model.setId(aqi.getId());
        model.setCalcDate(aqi.getCalcDate());
        model.setIndex(getIndexRestApiModel(aqi.getIndex()));

        return model;
    }

    public IndexRestApiModel getIndexRestApiModel(Index index){
        IndexRestApiModel model = new IndexRestApiModel();

        model.setId(index.getId());
        model.setName(index.getName());

        return model;
    }

    public PollutionDataRestApiModel getPollutionDataRestApiModel(PollutionData pollutionData){
        PollutionDataRestApiModel model = new PollutionDataRestApiModel();

        model.setId(pollutionData.getId());
        model.setParameter(getParameterRestApiModel(pollutionData.getParameter()));
        model.setDate(pollutionData.getDate());
        model.setValue(pollutionData.getValue());
        model.setPercentValue(pollutionData.getPercentValue());
        model.setIndex(getIndexRestApiModel(pollutionData.getIndex()));


        return model;
    }

    public ParameterRestApiModel getParameterRestApiModel(Parameter parameter){
        ParameterRestApiModel model = new ParameterRestApiModel();
        model.setId(parameter.getId());
        model.setParameterName(parameter.getParameterName());
        model.setParamteterFormula(parameter.getParameterFormula());

        return model;
    }
}
