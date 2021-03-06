package com.gruszka.airpollutionwebapp.service;

import com.gruszka.airpollutionwebapp.entity.Parameter;
import com.gruszka.airpollutionwebapp.gios.GIOSApiMapper;
import com.gruszka.airpollutionwebapp.gios.model.ParameterGIOSModel;
import com.gruszka.airpollutionwebapp.gios.model.SensorGIOSModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ParameterServiceImplTest {

    @Autowired
    private ParameterService parameterService;

    @Autowired
    private GIOSApiMapper giosApiMapper;

    protected final Logger LOG = Logger.getLogger(getClass().getName());


    @Test
    public void shouldReturnNotNullForFindingParametersByCity(){

        final Integer CITY_ID = 63;
        List<Parameter> parameterList = parameterService.findDistinctByCityId(CITY_ID);

        for(Parameter parameter : parameterList){
            LOG.info("Parameter: " + parameter.getParameterName());
        }
    }
}