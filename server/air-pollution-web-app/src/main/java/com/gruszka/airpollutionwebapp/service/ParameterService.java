package com.gruszka.airpollutionwebapp.service;

import com.gruszka.airpollutionwebapp.entity.Parameter;
import com.gruszka.airpollutionwebapp.entity.Sensor;
import com.gruszka.airpollutionwebapp.gios.model.ParameterGIOSModel;

import java.util.List;

public interface ParameterService {

    void save(Parameter parameter);

    void save(ParameterGIOSModel parameterGIOSModel);

    Parameter findByParameterName(String parameterName);

    Parameter findById(Integer id);

    List<Parameter> findDistinctByCityId(Integer cityId);

}
