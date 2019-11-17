package com.gruszka.airpollutionwebapp.dao;

import com.gruszka.airpollutionwebapp.entity.AirQualityIndex;
import com.gruszka.airpollutionwebapp.entity.Station;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AirQualityIndexDao extends JpaRepository<AirQualityIndex, Long> {


    Optional<AirQualityIndex> findByCalcDateAndStation(Date calcDate, Station station);

    @Query("SELECT a FROM AirQualityIndex a WHERE a.station = (:station) ORDER BY a.calcDate DESC")
    List<AirQualityIndex> findMostCurrentIndeciesForStation(@Param("station") Station station, Pageable pageable);

    default Optional<AirQualityIndex> findMostCurrentIndexForStation(Station station){
        List<AirQualityIndex> list = findMostCurrentIndeciesForStation(station, PageRequest.of(0,1));
        if(list.isEmpty()){
            return null;
        }
        else {
            return Optional.of(list.get(0));
        }
    }

}
