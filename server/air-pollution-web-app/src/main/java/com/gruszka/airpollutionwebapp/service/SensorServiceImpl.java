package com.gruszka.airpollutionwebapp.service;

import com.gruszka.airpollutionwebapp.dao.SensorDao;
import com.gruszka.airpollutionwebapp.entity.Parameter;
import com.gruszka.airpollutionwebapp.entity.Sensor;
import com.gruszka.airpollutionwebapp.entity.Station;
import com.gruszka.airpollutionwebapp.gios.GIOSModelAdapter;
import com.gruszka.airpollutionwebapp.gios.model.SensorGIOSModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class SensorServiceImpl implements SensorService {

    private SensorDao sensorDao;
    private GIOSModelAdapter giosModelAdapter;

    protected final Logger LOG = Logger.getLogger(getClass().getName());

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
            LOG.info("Sensor saved/updated: " + sensor.getIdApi());

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
    public Sensor findById(Integer id) {
        Optional<Sensor> result = sensorDao.findById(id);
        Sensor sensor;

        if(result.isPresent()){
            sensor = result.get();
        } else {
            throw new RuntimeException("Did not find Sensor by its Id: " + id);
        }

        return sensor;
    }

    @Override
    public List<Sensor> findAllByStationId(Integer id) {

        List<Sensor> sensors = sensorDao.findAllByStationId(id);
        return sensors;
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

    @Override
    public List<Sensor> findAllByStation(Station station) {

        List<Sensor> sensors = sensorDao.findAllByStation(station);

        if(sensors.isEmpty()){
            throw new RuntimeException("Did not find sensors from " + station.getStationName() + " station");
        } else {
            return sensors;
        }
    }

    @Override
    public Parameter findParameterBySensorId(Integer id) {
        Optional<Parameter> result = sensorDao.findParameterBySensorId(id);
        Parameter parameter;

        if(result.isPresent()){
            parameter = result.get();
        } else {
            throw new RuntimeException("Did not find Parameter from Sensor id: " + id);
        }

        return parameter;
    }
}
