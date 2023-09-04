package com.lightweight.taxiservice.DAO;

import com.lightweight.taxiservice.entity.Car;
import com.lightweight.taxiservice.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car,Long> {
    List<Car> findCarsByDriverIdIsNull();

    Optional<Car> findFirstByOrderByIdAsc();

    List<Car> findAllByStatus(String status);
    Page<Car> findCarsByModelContainingIgnoreCase(String model, Pageable pageable);
}
