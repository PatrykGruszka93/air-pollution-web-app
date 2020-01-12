package com.gruszka.airpollutionwebapp.dao;

import com.gruszka.airpollutionwebapp.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CityDao extends JpaRepository<City, Integer> {

    Optional<City> findByName(String name);

    @Query (value = "SELECT DISTINCT c FROM City c ORDER BY c.name")
    List<City> findAllOrderByName();

}
