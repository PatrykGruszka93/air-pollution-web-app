package com.gruszka.airpollutionwebapp.dao;

import com.gruszka.airpollutionwebapp.entity.Index;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IndexDao extends JpaRepository<Index, Integer> {

    Optional<Index> findById(Integer id);

    Optional<Index> findByName(String name);


}
