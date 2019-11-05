package com.gruszka.airpollutionwebapp.gios;

import com.gruszka.airpollutionwebapp.entity.*;
import com.gruszka.airpollutionwebapp.gios.model.*;
import com.gruszka.airpollutionwebapp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class GIOSModelAdapter {

    private AirQualityServiceService airQualityServiceService;
    private CityService cityService;
    private CommuneService communeService;
    private StationService stationService;
    private ParameterService parameterService;

    private static final String SERVICE_NAME = "GIOS";

    public Station getStation(StationGIOSModel model){

        Station station = new Station();

        station.setId(model.getId());
        station.setIdApi(model.getId());
        station.setService(airQualityServiceService.findByName(SERVICE_NAME));
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

    public Sensor getSensor(SensorGIOSModel model){

        Sensor sensor = new Sensor();

        sensor.setId(model.getId());
        sensor.setIdApi(model.getId());
        sensor.setStation(stationService.findByIdApiAndService(model.getStationId(), airQualityServiceService.findByName("GIOS")));
        sensor.setParameter(parameterService.findByParameterName(model.getParam().getParamName()));

        return sensor;
    }

    public Parameter getParameter(ParameterGIOSModel parameterGIOSModel) {

        Parameter parameter = new Parameter();

        parameter.setId(parameter.getId());
        parameter.setParameterName(parameterGIOSModel.getParamName());
        parameter.setParameterFormula(parameterGIOSModel.getParamFormula());
        parameter.setParameterCode(parameterGIOSModel.getParamCode());

        return parameter;
    }

    public PollutionData getPollutionData(PollutionDataValueGIOSModel valueModel, Sensor sensor){

        PollutionData pollutionData = new PollutionData();
        String datePattern = "yyyy-MM-dd HH:mm:ss";
        pollutionData.setId(-1L);
        try {
            String strDate = valueModel.getDate();
            pollutionData.setDate(parseDate(strDate, datePattern));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        pollutionData.setValue(valueModel.getValue());
        pollutionData.setSensor(sensor);
        pollutionData.setParameter(sensor.getParameter());

        return pollutionData;
    }

    private Date parseDate(String strDate, String pattern) throws ParseException {

        DateFormat sdf = new SimpleDateFormat(pattern);
        Date date;
        date = sdf.parse(strDate);

        return date;
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

    @Autowired
    public void setStationService(StationService stationService) {
        this.stationService = stationService;
    }

    @Autowired
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
}
