package com.gruszka.airpollutionwebapp.gios.adapter;

import com.gruszka.airpollutionwebapp.entity.Station;
import com.gruszka.airpollutionwebapp.gios.model.StationGIOSModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.logging.Logger;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GIOSModelAdapterTest {

    protected final Logger LOG = Logger.getLogger(getClass().getName());

    @Autowired
    private com.gruszka.airpollutionwebapp.gios.GIOSModelAdapter GIOSModelAdapter;

    @Test
    public void shouldAdaptStation(){

        StationGIOSModel model = new StationGIOSModel();

        model.setId(150);
        model.setStationName("Model Station");
        model.setGegrLat(50.2050);
        model.setGegrLon(45.5000);
        model.setCity(null);
        model.setAddressStreet("Model Street");

        Station station = GIOSModelAdapter.getStation(model);

        assertEquals(model.getId(), station.getId().intValue());
        assertEquals(model.getStationName(), station.getStationName());

        LOG.info("Station id: " + station.getId() + ", name: " + station.getStationName());

    }


}