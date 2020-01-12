package com.gruszka.airpollutionwebapp.dao;

import com.gruszka.airpollutionwebapp.entity.City;
import com.gruszka.airpollutionwebapp.entity.Parameter;
import com.gruszka.airpollutionwebapp.entity.PollutionDataHistory;
import com.gruszka.airpollutionwebapp.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.query.Param;


import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PollutionDataHistoryDao extends JpaRepository<PollutionDataHistory, Long> {

    Optional<PollutionDataHistory> findByDateAndSensorAndParameter(Date date, Sensor sensor, Parameter parameter);

    @Query(value = "SELECT new com.gruszka.airpollutionwebapp.entity.PollutionDataHistory(p.date, AVG(p.value)) FROM PollutionDataHistory p " +
            "WHERE p.sensor IN (" +
              "SELECT se.id FROM Sensor se " +
                "WHERE se.station IN (" +
                  "SELECT s.id FROM Station s " +
                    "WHERE s.city = (:city)) " +
                "AND se.parameter =(:parameter)) " +
            "AND DATE_FORMAT(p.date, :datePattern) = DATE_FORMAT(:filterDate, :datePattern) " +
            "GROUP BY DATE_FORMAT(p.date, :filterPattern) " +
            "ORDER BY DATE_FORMAT(p.date, :filterPattern)")
    List<PollutionDataHistory> findAvgDataByCityAndDateAndParameter(@Param("city") City city,
                                                                    @Param("filterDate") @Temporal(value = TemporalType.DATE) Date date,
                                                                    @Param("parameter")Parameter parameter,
                                                                    @Param("datePattern") String datePattern,
                                                                    @Param("filterPattern") String filterPattern);



}
