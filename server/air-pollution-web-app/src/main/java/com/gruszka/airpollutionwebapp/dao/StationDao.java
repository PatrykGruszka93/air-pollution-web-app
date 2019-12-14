package com.gruszka.airpollutionwebapp.dao;

import com.gruszka.airpollutionwebapp.entity.AirQualityService;
import com.gruszka.airpollutionwebapp.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StationDao extends JpaRepository<Station, Long> {


    Optional<Station> findByStationName(String stationName);

    Optional<Station> findByIdApiAndService(int idApi, AirQualityService service);

    //@Query("SELECT s from Station s JOIN FETCH s.sensors WHERE s.service = (:service)")
    List<Station> findAllByService(@Param("service") AirQualityService service);

    @Query("SELECT s from Station s JOIN FETCH s.city c JOIN FETCH c.commune WHERE s.service = (:service)")
    List<Station> findStationsBasicDetailsByService1(@Param("service") AirQualityService service);

    @Query(value =
            "SELECT * FROM station st JOIN city c ON st.city_id = c.id WHERE st.id IN(" +
            "SELECT id FROM sensor s WHERE s.id IN (SELECT lastest_data.sensor_id FROM (SELECT id, value, max(date), sensor_id FROM data_value GROUP BY sensor_id) " +
            "AS lastest_data INNER JOIN data_value ON data_value.id = lastest_data.id WHERE data_value.value>0)) AND service_id = 1;",
            nativeQuery = true)
    List<Station> findStationsBasicDetailsByService(@Param("service") AirQualityService service);



}
