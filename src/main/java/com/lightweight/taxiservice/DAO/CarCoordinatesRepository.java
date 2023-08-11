package com.lightweight.taxiservice.DAO;

import com.lightweight.taxiservice.entity.Car;
import com.lightweight.taxiservice.entity.CarCoordinates;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarCoordinatesRepository extends JpaRepository<CarCoordinates,Long> {
    Optional<CarCoordinates> findFirstByOrderByIdAsc();
}
