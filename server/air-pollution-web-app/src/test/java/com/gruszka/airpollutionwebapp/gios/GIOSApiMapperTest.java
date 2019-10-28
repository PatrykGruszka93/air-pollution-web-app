package com.gruszka.airpollutionwebapp.gios;

import com.gruszka.airpollutionwebapp.gios.model.PollutionData;
import com.gruszka.airpollutionwebapp.gios.model.PollutionDataValue;
import com.gruszka.airpollutionwebapp.gios.model.Sensor;
import com.gruszka.airpollutionwebapp.gios.model.Station;
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
        List<Station> stations = gios.getAllStations();

        assertNotNull(stations);
        for(Station station : stations){
            LOG.log(Level.INFO ,"Id: " + station.getId() + ", name: " + station.getStationName());
        }
    }

    @Test
    public void shouldReturnSensorsFromStation(){
        GIOSApiMapper gios = new GIOSApiMapper();
        List<Sensor> sensors = gios.getSensorsFromStation(14);

        assertNotNull(sensors);
        for(Sensor sensor: sensors){
            LOG.log(Level.INFO ,"Id: " + sensor.getId() + ", name: " + sensor.getParam().getParamName());
        }
    }

    @Test
    public void shouldReturnDataFromSensor(){
        GIOSApiMapper gios = new GIOSApiMapper();
        PollutionData pollutionData = gios.getDataFromSensor(92);

        assertNotNull(pollutionData);
        LOG.log(Level.INFO ,"Id: " + pollutionData.getKey());
        for(PollutionDataValue value : pollutionData.getValues()){
            LOG.log(Level.INFO, "Date: " + value.getDate() + ", value: " + value.getValue());
        }
    }

}