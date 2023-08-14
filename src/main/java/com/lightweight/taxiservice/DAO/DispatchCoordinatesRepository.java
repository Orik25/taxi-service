package com.lightweight.taxiservice.DAO;

import com.lightweight.taxiservice.entity.DispatchCoordinates;
import com.lightweight.taxiservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DispatchCoordinatesRepository extends JpaRepository<DispatchCoordinates, Long> {

    Optional<DispatchCoordinates> findFirstByOrderByIdAsc();
}
