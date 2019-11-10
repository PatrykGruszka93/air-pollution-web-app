package com.gruszka.airpollutionwebapp.service;

import com.gruszka.airpollutionwebapp.dao.ParameterDao;
import com.gruszka.airpollutionwebapp.entity.Parameter;
import com.gruszka.airpollutionwebapp.entity.Sensor;
import com.gruszka.airpollutionwebapp.gios.GIOSModelAdapter;
import com.gruszka.airpollutionwebapp.gios.model.ParameterGIOSModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParameterServiceImpl implements ParameterService{

    private ParameterDao parameterDao;
    private GIOSModelAdapter giosModelAdapter;


    @Autowired
    public ParameterServiceImpl(ParameterDao parameterDao, GIOSModelAdapter giosModelAdapter) {
        this.parameterDao = parameterDao;
        this.giosModelAdapter = giosModelAdapter;
    }

    @Override
    public void save(Parameter parameter) {

        Parameter tmpParameter;
        try{
            tmpParameter = findByParameterName(parameter.getParameterName());
            parameter.setId(tmpParameter.getId());
        } catch (RuntimeException e){
            parameter.setId(-1);
        } finally {
            parameterDao.save(parameter);
        }

    }

    @Override
    public void save(ParameterGIOSModel parameterGIOSModel) {

        Parameter parameter;
        parameter = giosModelAdapter.getParameter(parameterGIOSModel);
        save(parameter);

    }

    @Override
    public Parameter findByParameterName(String parameterName) {

        Optional<Parameter> result = parameterDao.findByParameterName(parameterName);
        Parameter parameter;

        if(result.isPresent()){
            parameter = result.get();
        } else {
            throw new RuntimeException("Did not find Parameter: " + parameterName);
        }

        return parameter;
    }

}
