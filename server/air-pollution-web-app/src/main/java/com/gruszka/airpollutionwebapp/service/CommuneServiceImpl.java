package com.gruszka.airpollutionwebapp.service;

import com.gruszka.airpollutionwebapp.dao.CommuneDao;
import com.gruszka.airpollutionwebapp.entity.Commune;
import com.gruszka.airpollutionwebapp.gios.GIOSModelAdapter;
import com.gruszka.airpollutionwebapp.gios.model.CommuneGIOSModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class CommuneServiceImpl implements CommuneService{

    private CommuneDao communeDao;
    private GIOSModelAdapter giosModelAdapter;

    protected final Logger LOG = Logger.getLogger(getClass().getName());

    @Autowired
    public CommuneServiceImpl(CommuneDao communeDao, GIOSModelAdapter giosModelAdapter) {
        this.communeDao = communeDao;
        this.giosModelAdapter = giosModelAdapter;
    }

    @Override
    public Commune findByCommuneName(String communeName) {

        Optional<Commune> result = communeDao.findByCommuneName(communeName);
        Commune commune;
        if(result.isPresent()){
            commune = result.get();
        }
        else {
            throw new RuntimeException("Did not find Commune: " + communeName);
        }
        return commune;
    }

    @Override
    public void save(Commune commune) {
        Commune tmpCommune;
        try{
            tmpCommune = findByCommuneName(commune.getCommuneName());
            commune.setId(tmpCommune.getId());
        } catch (RuntimeException e){
            commune.setId(-1);
        } finally {
            communeDao.save(commune);
            LOG.info("Commune saved/updated: " + commune.getCommuneName() + " : " + commune.getProvinceName() +
            " : " + commune.getDistrictName());
        }
    }

    @Override
    public void save(CommuneGIOSModel communeGIOSModel) {
        Commune commune;
        commune = giosModelAdapter.getCommune(communeGIOSModel);
        save(commune);
    }

    @Override
    public void saveAll(List<CommuneGIOSModel> communes) {
        Commune commune;
        for(CommuneGIOSModel communeModel : communes){
            commune = giosModelAdapter.getCommune(communeModel);
            save(commune);
        }
    }
}
