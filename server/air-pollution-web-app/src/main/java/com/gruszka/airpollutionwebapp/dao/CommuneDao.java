package com.gruszka.airpollutionwebapp.dao;

import com.gruszka.airpollutionwebapp.entity.Commune;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommuneDao extends JpaRepository<Commune, Long> {

    Optional<Commune> findByCommuneName(String communeName);
}
