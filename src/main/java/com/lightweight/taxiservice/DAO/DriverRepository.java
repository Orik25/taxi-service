package com.lightweight.taxiservice.DAO;

import com.lightweight.taxiservice.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver, Long> {
    Optional<Driver> findFirstByOrderByIdAsc();

    @Query(value = "SELECT drivers.id, drivers.first_name, drivers.last_name,drivers.phone,drivers.email FROM drivers\n" +
            "LEFT JOIN cars ON drivers.id = cars.driver_id\n" +
            "WHERE cars.driver_id IS NULL", nativeQuery = true)
    List<Driver> findDriversWithoutCars();
}
