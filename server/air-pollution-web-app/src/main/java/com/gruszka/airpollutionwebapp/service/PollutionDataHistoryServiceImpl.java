package com.gruszka.airpollutionwebapp.service;

import com.gruszka.airpollutionwebapp.dao.PollutionDataHistoryDao;
import com.gruszka.airpollutionwebapp.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class PollutionDataHistoryServiceImpl implements PollutionDataHistoryService{

    private PollutionDataHistoryDao pollutionDataHistoryDao;
    private PollutionDataService pollutionDataService;
    private IndexService indexService;
    private ParameterService parameterService;
    private SensorService sensorService;

    protected final Logger LOG = Logger.getLogger(getClass().getName());

    @Autowired
    public PollutionDataHistoryServiceImpl(PollutionDataHistoryDao pollutionDataHistoryDao, PollutionDataService pollutionDataService,
                                           IndexService indexService, ParameterService parameterService, SensorService sensorService) {
        this.pollutionDataHistoryDao = pollutionDataHistoryDao;
        this.pollutionDataService = pollutionDataService;
        this.indexService = indexService;
        this.parameterService = parameterService;
        this.sensorService = sensorService;
    }

    @Override
    public void transferDataIntoDataHistory() {
        List<PollutionDataHistory> dataList = pollutionDataService.findDataForTransferIntoHistoryTable();

        for(PollutionDataHistory data : dataList){
            data.setParameter(parameterService.findById(data.getParameter().getId()));
            data.setSensor(sensorService.findById(data.getSensor().getId()));
            data.setPercentValue(calculatePercentValue(data));
            data.setIndex(indexService.choosePollutionIndexByItsValue(data));
            save(data);

        }
    }

    private int calculatePercentValue(PollutionDataHistory data) throws RuntimeException{
        Parameter parameter = data.getParameter();

        switch(parameter.getId()) {
            case 1 : return data.getValue().intValue() * 100 / 350;     //SO2
            case 2 : return data.getValue().intValue() * 100 / 5;       //C6H6
            case 3 : return data.getValue().intValue() * 100 / 10000 ;  //CO
            case 4 : return data.getValue().intValue() * 100 / 200 ;    //NO2
            case 5 : return data.getValue().intValue() * 100 / 120 ;    //O3
            case 6 : return data.getValue().intValue() * 100 / 25 ;     //PM2.5
            case 7 : return data.getValue().intValue() * 100 / 50 ;     //PM10
            default : throw new RuntimeException("Wrong parameter formula");
        }
    }

    @Override
    public void save(PollutionDataHistory data) {
        PollutionDataHistory tmpData;

        try {
            tmpData = findByDateAndSensorAndParameter(data.getDate(), data.getSensor(), data.getParameter());
            data.setId(tmpData.getId());

        }catch (RuntimeException e){
            data.setId(-1L);
        }finally {
            pollutionDataHistoryDao.save(data);
            LOG.info("Pollution data saved/updated: " + data.getDate() + " : " + data.getValue() + " : "
                    + data.getPercentValue() + "% : " + data.getIndex().getName());
        }
    }

    @Override
    public PollutionDataHistory findByDateAndSensorAndParameter(Date date, Sensor sensor, Parameter parameter) throws RuntimeException{

        Optional<PollutionDataHistory> result = pollutionDataHistoryDao.findByDateAndSensorAndParameter(date, sensor, parameter);
        PollutionDataHistory data;

        if(result.isPresent()){
            data = result.get();
        } else {
            throw new RuntimeException("Did not find Data: " + date + " : " + sensor.getIdApi());
        }
        return data;
    }
}
