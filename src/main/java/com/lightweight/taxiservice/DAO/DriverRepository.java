package com.lightweight.taxiservice.DAO;

import com.lightweight.taxiservice.entity.Driver;
import com.lightweight.taxiservice.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver, Long> {
    Optional<Driver> findFirstByOrderByIdAsc();

    Optional<Driver> findByEmail(String email);

    @Query(value = "SELECT drivers.id, drivers.first_name, drivers.last_name,drivers.phone,drivers.email FROM drivers\n" +
            "LEFT JOIN cars ON drivers.id = cars.driver_id\n" +
            "WHERE cars.driver_id IS NULL", nativeQuery = true)
    List<Driver> findDriversWithoutCars();

    Page<Driver> findDriversByLastNameContainingIgnoreCase(String model, Pageable pageable);
}
