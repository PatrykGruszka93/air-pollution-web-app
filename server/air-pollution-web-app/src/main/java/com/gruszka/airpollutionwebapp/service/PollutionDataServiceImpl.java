package com.gruszka.airpollutionwebapp.service;

import com.gruszka.airpollutionwebapp.dao.PollutionDataDao;
import com.gruszka.airpollutionwebapp.entity.Parameter;
import com.gruszka.airpollutionwebapp.entity.PollutionData;
import com.gruszka.airpollutionwebapp.entity.Sensor;
import com.gruszka.airpollutionwebapp.gios.GIOSModelAdapter;
import com.gruszka.airpollutionwebapp.gios.model.PollutionDataGIOSModel;
import com.gruszka.airpollutionwebapp.gios.model.PollutionDataValueGIOSModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class PollutionDataServiceImpl implements PollutionDataService {

    private PollutionDataDao pollutionDataDao;
    private IndexService indexService;
    private GIOSModelAdapter giosModelAdapter;
    private SensorService sensorService;

    protected final Logger LOG = Logger.getLogger(getClass().getName());

    @Autowired
    public PollutionDataServiceImpl(PollutionDataDao pollutionDataDao, IndexService indexService, GIOSModelAdapter giosModelAdapter,
                                    SensorService sensorService) {
        this.pollutionDataDao = pollutionDataDao;
        this.indexService = indexService;
        this.giosModelAdapter = giosModelAdapter;
        this.sensorService = sensorService;
    }

    @Override
    public void saveAll(PollutionDataGIOSModel pollutionDataModel, Sensor sensor) {
        PollutionData pollutionData;

        for(PollutionDataValueGIOSModel pollutionDataValue : pollutionDataModel.getValues()){
            pollutionData = giosModelAdapter.getPollutionData(pollutionDataValue, sensor);
            save(pollutionData);
        }
    }

    @Override
    public void save(PollutionData pollutionData) {
        PollutionData tmpPollutionData;

        try {
            tmpPollutionData = findByDateAndSensorAndParameter(pollutionData.getDate(), pollutionData.getSensor(), pollutionData.getParameter());
            pollutionData.setId(tmpPollutionData.getId());

        }catch (RuntimeException e){
            pollutionData.setId(-1L);
        }finally {
            pollutionData.setPercentValue(calculatePercentValue(pollutionData));
            pollutionData.setIndex(indexService.choosePollutionIndexByItsValue(pollutionData));
            pollutionDataDao.save(pollutionData);
            LOG.info("Pollution data saved/updated: " + pollutionData.getDate() + " : " + pollutionData.getValue() + " : "
            + pollutionData.getPercentValue() + "% : " + pollutionData.getIndex().getName());
        }
    }

    @Override
    public PollutionData findByDateAndSensorAndParameter(Date date, Sensor sensor, Parameter parameter) throws RuntimeException{

        Optional<PollutionData> result = pollutionDataDao.findByDateAndSensorAndParameter(date, sensor, parameter);
        PollutionData pollutionData;

        if(result.isPresent()){
            pollutionData = result.get();
        } else {
            throw new RuntimeException("Did not find Data: " + date + " : " + sensor.getIdApi());
        }
        return pollutionData;
    }

    private int calculatePercentValue(PollutionData pollutionData) throws RuntimeException{

        Parameter parameter = pollutionData.getParameter();

        final String PARAMETER_FORMULA = parameter.getParameterFormula();

        switch(PARAMETER_FORMULA) {
            case "SO2" : return pollutionData.getValue().intValue() * 100 / 350;
            case "C6H6" : return pollutionData.getValue().intValue() * 100 / 5;
            case "CO" : return pollutionData.getValue().intValue() * 100 / 10000 ;
            case "NO2" : return pollutionData.getValue().intValue() * 100 / 200 ;
            case "O3" : return pollutionData.getValue().intValue() * 100 / 120 ;
            case "PM2.5" : return pollutionData.getValue().intValue() * 100 / 25 ;
            case "PM10" : return pollutionData.getValue().intValue() * 100 / 50 ;
            default : throw new RuntimeException("Wrong parameter formula");
        }
    }

}
