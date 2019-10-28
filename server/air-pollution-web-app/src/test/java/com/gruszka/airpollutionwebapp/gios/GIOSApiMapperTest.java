package com.gruszka.airpollutionwebapp.gios;

import com.gruszka.airpollutionwebapp.gios.model.PollutionDataGIOSModel;
import com.gruszka.airpollutionwebapp.gios.model.PollutionDataValueGIOSModel;
import com.gruszka.airpollutionwebapp.gios.model.SensorGIOSModel;
import com.gruszka.airpollutionwebapp.gios.model.StationGIOSModel;
import org.junit.Test;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.*;

public class GIOSApiMapperTest {

    protected final Logger LOG = Logger.getLogger(getClass().getName());

    @Test
    public void shouldReturnStations(){
        GIOSApiMapper gios = new GIOSApiMapper();
        List<StationGIOSModel> stationGIOSModels = gios.getAllStations();

        assertNotNull(stationGIOSModels);
        for(StationGIOSModel stationGIOSModel : stationGIOSModels){
            LOG.log(Level.INFO ,"Id: " + stationGIOSModel.getId() + ", name: " + stationGIOSModel.getStationName());
        }
    }

    @Test
    public void shouldReturnSensorsFromStation(){
        GIOSApiMapper gios = new GIOSApiMapper();
        List<SensorGIOSModel> sensorGIOSModels = gios.getSensorsFromStation(14);

        assertNotNull(sensorGIOSModels);
        for(SensorGIOSModel sensorGIOSModel : sensorGIOSModels){
            LOG.log(Level.INFO ,"Id: " + sensorGIOSModel.getId() + ", name: " + sensorGIOSModel.getParam().getParamName());
        }
    }

    @Test
    public void shouldReturnDataFromSensor(){
        GIOSApiMapper gios = new GIOSApiMapper();
        PollutionDataGIOSModel pollutionDataGIOSModel = gios.getDataFromSensor(92);

        assertNotNull(pollutionDataGIOSModel);
        LOG.log(Level.INFO ,"Id: " + pollutionDataGIOSModel.getKey());
        for(PollutionDataValueGIOSModel value : pollutionDataGIOSModel.getValues()){
            LOG.log(Level.INFO, "Date: " + value.getDate() + ", value: " + value.getValue());
        }
    }

}