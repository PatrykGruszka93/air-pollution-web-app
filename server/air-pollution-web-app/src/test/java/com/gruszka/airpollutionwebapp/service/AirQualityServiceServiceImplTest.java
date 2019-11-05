package com.gruszka.airpollutionwebapp.service;

import com.gruszka.airpollutionwebapp.entity.AirQualityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AirQualityServiceServiceImplTest {

    @Autowired
    private AirQualityServiceServiceImpl airQualityServiceService;

    protected final Logger LOG = Logger.getLogger(getClass().getName());

    @Test
    public void whenServicesAreFoundReturnTrue() {

        List<AirQualityService> serviceList = airQualityServiceService.findAll();

        for(AirQualityService aqs : serviceList){
            LOG.log(Level.INFO ,"Id: " + aqs.getId() + ", name: " + aqs.getName());
        }
        assertNotNull(serviceList);
    }

    @Test
    public void shouldReturnInstanceOfGIOSService(){

        AirQualityService service = airQualityServiceService.findByName("GIOS");
        assertEquals("GIOS", service.getName());
    }

}