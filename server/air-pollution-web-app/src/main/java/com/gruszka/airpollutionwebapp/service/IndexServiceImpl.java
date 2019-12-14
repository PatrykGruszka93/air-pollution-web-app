package com.gruszka.airpollutionwebapp.service;

import com.gruszka.airpollutionwebapp.dao.IndexDao;
import com.gruszka.airpollutionwebapp.entity.Index;
import com.gruszka.airpollutionwebapp.entity.PollutionData;
import com.gruszka.airpollutionwebapp.entity.PollutionDataHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IndexServiceImpl implements IndexService {

    private IndexDao indexDao;

    @Autowired
    public IndexServiceImpl(IndexDao indexDao) {
        this.indexDao = indexDao;
    }

    @Override
    public Index findById(Integer id) {
        Optional<Index> result = indexDao.findById(id);
        Index index;

        if(result.isPresent()){
            index = result.get();
        } else {
            throw new RuntimeException("Did not find Index by Id: " + id);
        }
        return index;
    }

    @Override
    public Index findByName(String name) {
        Optional<Index> result = indexDao.findByName(name);
        Index index;

        if(result.isPresent()){
            index = result.get();
        } else {
            throw new RuntimeException("Did not find Index by Name: " + name);
        }
        return index;
    }

    public Index choosePollutionIndexByItsValue(PollutionData pollutionData) throws RuntimeException{

        Double pollutionValue = pollutionData.getValue();

        switch(pollutionData.getParameter().getParameterFormula()){
            case "SO2" : return compareSO2WithIndexTable(pollutionValue);
            case "C6H6" : return compareC6H6WithIndexTable(pollutionValue);
            case "CO" : return compareCOWithIndexTable(pollutionValue);
            case "NO2" : return compareNO2WithIndexTable(pollutionValue);
            case "O3" : return compareO3WithIndexTable(pollutionValue);
            case "PM2.5" : return comparePM2_5WithIndexTable(pollutionValue);
            case "PM10" : return comparePM10WithIndexTable(pollutionValue);
            default : throw new RuntimeException("Wrong parameter formula");
        }
    }

    @Override
    public Index choosePollutionIndexByItsValue(PollutionDataHistory pollutionData) {

        Double pollutionValue = pollutionData.getValue();
        switch(pollutionData.getParameter().getParameterFormula()){
            case "SO2" : return compareSO2WithIndexTable(pollutionValue);
            case "C6H6" : return compareC6H6WithIndexTable(pollutionValue);
            case "CO" : return compareCOWithIndexTable(pollutionValue);
            case "NO2" : return compareNO2WithIndexTable(pollutionValue);
            case "O3" : return compareO3WithIndexTable(pollutionValue);
            case "PM2.5" : return comparePM2_5WithIndexTable(pollutionValue);
            case "PM10" : return comparePM10WithIndexTable(pollutionValue);
            default : throw new RuntimeException("Wrong parameter formula");
        }
    }

    private Index compareSO2WithIndexTable(Double pollutionValue) {

        if (pollutionValue == null || pollutionValue < 0){
            return findById(7);                         // brak indeksu
        }
        if (pollutionValue <= 51.0) return findById(1); // bardzo dobry
        if (pollutionValue <= 101.0) return findById(2);// dobry
        if (pollutionValue <= 201.0) return findById(3);// umikarkowany
        if (pollutionValue <= 351.0) return findById(4);// dostateczny
        if (pollutionValue <= 501.0) return findById(5);// zły
        else return findById(6);                        // bardzo zły
    }

    private Index compareC6H6WithIndexTable(Double pollutionValue) {
        if (pollutionValue == null || pollutionValue < 0){
            return findById(7);                         // brak indeksu
        }
        if (pollutionValue <= 6.0) return findById(1);  // bardzo dobry
        if (pollutionValue <= 11.0) return findById(2); // dobry
        if (pollutionValue <= 16.0) return findById(3); // umikarkowany
        if (pollutionValue <= 21.0) return findById(4); // dostateczny
        if (pollutionValue <= 51.0) return findById(5); // zły
        else return findById(6);                        // bardzo zły
    }

    private Index compareCOWithIndexTable(Double pollutionValue) {
        if (pollutionValue == null || pollutionValue < 0){
            return findById(7);                            // brak indeksu
        }
        if (pollutionValue <= 3000.0) return findById(1);  // bardzo dobry
        if (pollutionValue <= 7000.0) return findById(2);  // dobry
        if (pollutionValue <= 11000.0) return findById(3); // umikarkowany
        if (pollutionValue <= 15000.0) return findById(4); // dostateczny
        if (pollutionValue <= 21000.0) return findById(5); // zły
        else return findById(6);                           // bardzo zły
    }

    private Index compareNO2WithIndexTable(Double pollutionValue) {
        if (pollutionValue == null || pollutionValue < 0){
            return findById(7);                          // brak indeksu
        }
        if (pollutionValue <= 41.0) return findById(1);  // bardzo dobry
        if (pollutionValue <= 101.0) return findById(2); // dobry
        if (pollutionValue <= 151.0) return findById(3); // umikarkowany
        if (pollutionValue <= 201.0) return findById(4); // dostateczny
        if (pollutionValue <= 401.0) return findById(5); // zły
        else return findById(6);                         // bardzo zły
    }

    private Index compareO3WithIndexTable(Double pollutionValue) {
        if (pollutionValue == null || pollutionValue < 0){
            return findById(7);                          // brak indeksu
        }
        if (pollutionValue <= 71.0) return findById(1);  // bardzo dobry
        if (pollutionValue <= 121.0) return findById(2); // dobry
        if (pollutionValue <= 151.0) return findById(3); // umikarkowany
        if (pollutionValue <= 181.0) return findById(4); // dostateczny
        if (pollutionValue <= 241.0) return findById(5); // zły
        else return findById(6);                         // bardzo zły
    }

    private Index comparePM2_5WithIndexTable(Double pollutionValue) {
        if (pollutionValue == null || pollutionValue < 0){
            return findById(7);                         // brak indeksu
        }
        if (pollutionValue <= 13.0) return findById(1); // bardzo dobry
        if (pollutionValue <= 37.0) return findById(2); // dobry
        if (pollutionValue <= 61.0) return findById(3); // umikarkowany
        if (pollutionValue <= 85.0) return findById(4); // dostateczny
        if (pollutionValue <= 121.0) return findById(5);// zły
        else return findById(6);                        // bardzo zły
    }

    private Index comparePM10WithIndexTable(Double pollutionValue) {
        if (pollutionValue == null || pollutionValue < 0){
            return findById(7);                         // brak indeksu
        }
        if (pollutionValue <= 21.0) return findById(1); // bardzo dobry
        if (pollutionValue <= 61.0) return findById(2); // dobry
        if (pollutionValue <= 101.0) return findById(3);// umikarkowany
        if (pollutionValue <= 141.0) return findById(4);// dostateczny
        if (pollutionValue <= 201.0) return findById(5);// zły
        else return findById(6);                        // bardzo zły
    }

}
