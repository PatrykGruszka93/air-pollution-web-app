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
    private GIOSModelAdapter giosModelAdapter;

    protected final Logger LOG = Logger.getLogger(getClass().getName());

    @Autowired
    public PollutionDataServiceImpl(PollutionDataDao pollutionDataDao, GIOSModelAdapter giosModelAdapter) {
        this.pollutionDataDao = pollutionDataDao;
        this.giosModelAdapter = giosModelAdapter;
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
            pollutionDataDao.save(pollutionData);
            LOG.info("Pollution data saved/updated: " + pollutionData.getDate() + " : " + pollutionData.getValue());
        }
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
    public PollutionData findByDateAndSensorAndParameter(Date date, Sensor sensor, Parameter parameter) {

        Optional<PollutionData> result = pollutionDataDao.findByDateAndSensorAndParameter(date, sensor, parameter);
        PollutionData pollutionData;

        if(result.isPresent()){
            pollutionData = result.get();
        } else {
            throw new RuntimeException("Did not find Data: " + date + " : " + sensor.getIdApi());
        }
        return pollutionData;
    }
}
