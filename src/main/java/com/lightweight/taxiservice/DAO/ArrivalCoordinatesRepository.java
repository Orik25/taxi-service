package com.lightweight.taxiservice.DAO;

import com.lightweight.taxiservice.entity.ArrivalCoordinates;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArrivalCoordinatesRepository extends JpaRepository<ArrivalCoordinates, Long> {

    Optional<ArrivalCoordinates> findFirstByOrderByIdAsc();
}