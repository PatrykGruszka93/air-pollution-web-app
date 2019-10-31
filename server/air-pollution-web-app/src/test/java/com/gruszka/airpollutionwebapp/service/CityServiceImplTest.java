package com.gruszka.airpollutionwebapp.service;

import com.gruszka.airpollutionwebapp.gios.GIOSApiMapper;
import com.gruszka.airpollutionwebapp.gios.model.CityGIOSModel;
import com.gruszka.airpollutionwebapp.gios.model.CommuneGIOSModel;
import com.gruszka.airpollutionwebapp.gios.model.StationGIOSModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CityServiceImplTest {

    @Autowired
    CityService cityService;

    @Autowired
    private GIOSApiMapper giosApiMapper;

    @Test
    public void shouldSaveAllCitiesFromGIOSApi(){

        List<StationGIOSModel> stationList = giosApiMapper.getAllStations();
        CityGIOSModel cityModel;

        for(StationGIOSModel stationGIOSModel : stationList){
            try{
                cityModel = stationGIOSModel.getCity();
                cityService.save(cityModel);
            }catch (NullPointerException e){
                continue;
            }
        }
    }

}