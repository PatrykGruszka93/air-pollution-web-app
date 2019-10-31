package com.gruszka.airpollutionwebapp.dao;

import com.gruszka.airpollutionwebapp.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityDao extends JpaRepository<City, Long> {

    Optional<City> findByName(String name);

}
