package com.gruszka.airpollutionwebapp.service;

import com.gruszka.airpollutionwebapp.entity.Parameter;
import com.gruszka.airpollutionwebapp.entity.Sensor;
import com.gruszka.airpollutionwebapp.gios.model.ParameterGIOSModel;

public interface ParameterService {

    void save(Parameter parameter);

    void save(ParameterGIOSModel parameterGIOSModel);

    Parameter findByParameterName(String parameterName);

}
