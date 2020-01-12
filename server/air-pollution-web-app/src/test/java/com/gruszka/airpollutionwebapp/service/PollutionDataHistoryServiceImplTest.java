package com.gruszka.airpollutionwebapp.service;

import com.gruszka.airpollutionwebapp.entity.City;
import com.gruszka.airpollutionwebapp.entity.Parameter;
import com.gruszka.airpollutionwebapp.entity.PollutionDataHistory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PollutionDataHistoryServiceImplTest {

    @Autowired
    private PollutionDataHistoryService pollutionDataHistoryService;

    @Autowired
    private CityService cityService;

    @Autowired
    private ParameterService parameterService;

    protected final Logger LOG = Logger.getLogger(getClass().getName());

    @Test
    public void findAvgDataByCityAndDateAndParameter() throws ParseException {
        City city = cityService.findByName("Warszawa");
        Parameter parameter = parameterService.findById(4);
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2019-12-02");
        String datePattern = "'%Y-%m'";
        LOG.info("Getting data from city: " + city.getName() + ", paramteter: " + parameter.getId() + ", date: " + date.toString());


        List<PollutionDataHistory> dataList =
                pollutionDataHistoryService.findAvgDataByCityAndDateAndParameter(city, date, parameter, datePattern);

        for(PollutionDataHistory data : dataList){
            LOG.info("Got data ----> date: " + data.getDate() + " value " + data.getValue());
        }

    }

}