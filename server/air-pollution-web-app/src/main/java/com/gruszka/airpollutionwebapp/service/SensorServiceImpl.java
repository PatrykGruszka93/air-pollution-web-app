package com.gruszka.airpollutionwebapp.service;

import com.gruszka.airpollutionwebapp.dao.SensorDao;
import com.gruszka.airpollutionwebapp.entity.Sensor;
import com.gruszka.airpollutionwebapp.entity.Station;
import com.gruszka.airpollutionwebapp.gios.GIOSModelAdapter;
import com.gruszka.airpollutionwebapp.gios.model.SensorGIOSModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SensorServiceImpl implements SensorService {

    private SensorDao sensorDao;
    private GIOSModelAdapter giosModelAdapter;

    public SensorServiceImpl(SensorDao sensorDao, GIOSModelAdapter giosModelAdapter) {
        this.sensorDao = sensorDao;
        this.giosModelAdapter = giosModelAdapter;
    }

    @Override
    public void save(Sensor sensor) {

        Sensor tmpSensor;
        try{
            tmpSensor = findByIdApiAndStation(sensor.getIdApi(), sensor.getStation());
            sensor.setId(tmpSensor.getId());
        } catch (RuntimeException e){
            sensor.setId(-1);
        }
        finally {
            sensorDao.save(sensor);
        }

    }

    @Override
    public void saveAll(List<SensorGIOSModel> sensors) {

        Sensor sensor;
        for(SensorGIOSModel sensorModel : sensors){
            sensor = giosModelAdapter.getSensor(sensorModel);
            save(sensor);
        }
    }

    @Override
    public Sensor findByIdApiAndStation(int idApi, Station station) {

        Optional<Sensor> result = sensorDao.findByIdApiAndStation(idApi, station);
        Sensor sensor;

        if(result.isPresent()){
            sensor = result.get();
        } else {
            throw new RuntimeException("Did not find Sensor: " + idApi);
        }

        return sensor;
    }
}
