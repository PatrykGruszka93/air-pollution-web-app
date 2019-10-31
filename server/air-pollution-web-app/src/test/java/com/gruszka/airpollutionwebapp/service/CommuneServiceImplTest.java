package com.gruszka.airpollutionwebapp.service;


import com.gruszka.airpollutionwebapp.gios.GIOSApiMapper;
import com.gruszka.airpollutionwebapp.gios.model.CommuneGIOSModel;
import com.gruszka.airpollutionwebapp.gios.model.StationGIOSModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommuneServiceImplTest {

    @Autowired
    private CommuneService communeService;

    @Autowired
    private GIOSApiMapper giosApiMapper;

    @Test
    public void shouldSaveAllCommunesFromGIOSApi(){

        List<StationGIOSModel> stationList = giosApiMapper.getAllStations();
        CommuneGIOSModel communeModel;

        for(StationGIOSModel stationGIOSModel : stationList){
            try{
                communeModel = stationGIOSModel.getCity().getCommune();
                communeService.save(communeModel);
            }catch (NullPointerException e){
                continue;
            }
        }
    }

}