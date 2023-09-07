package com.lightweight.taxiservice.DAO;

import com.lightweight.taxiservice.DTO.car.CarForUpdateDriverDTO;
import com.lightweight.taxiservice.entity.Car;
import com.lightweight.taxiservice.entity.Driver;
import com.lightweight.taxiservice.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car,Long> {
    List<Car> findCarsByDriverIdIsNull();

    Optional<Car> findFirstByOrderByIdAsc();

    List<Car> findAllByStatus(String status);
    Page<Car> findCarsByModelContainingIgnoreCase(String model, Pageable pageable);

    @Query(value = "SELECT cars.id, cars.brand, cars.model, cars.capacity, cars.year, cars.status, cars.category, cars.driver_id FROM cars " +
            "LEFT JOIN drivers ON drivers.id = cars.driver_id " +
            "WHERE cars.driver_id IS NULL", nativeQuery = true)
    List<Car> findCarsWithoutDrivers();
}
