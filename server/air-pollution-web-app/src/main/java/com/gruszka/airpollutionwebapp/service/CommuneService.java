package com.gruszka.airpollutionwebapp.service;

import com.gruszka.airpollutionwebapp.entity.Commune;
import com.gruszka.airpollutionwebapp.gios.model.CommuneGIOSModel;

import java.util.List;

public interface CommuneService {

    Commune findByCommuneName(String communeName);

    void save(Commune commune);

    void save(CommuneGIOSModel communeGIOSModel);

    void saveAll(List<CommuneGIOSModel> communes);

}
