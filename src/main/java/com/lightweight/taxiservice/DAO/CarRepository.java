package com.lightweight.taxiservice.DAO;

import com.lightweight.taxiservice.DTO.car.CarForUpdateDriverDTO;
import com.lightweight.taxiservice.entity.Car;
import com.lightweight.taxiservice.entity.Driver;
import com.lightweight.taxiservice.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
    @Query(value = "SELECT c.*, d.first_name, d.last_name " +
            "FROM cars c " +
            "INNER JOIN drivers d ON d.id = c.driver_id " +
            "INNER JOIN car_coordinates ON c.id = car_coordinates.car_id " +
            "WHERE " +
            "CASE " +
            "WHEN :fieldName = 'brand' THEN LOWER(c.brand) LIKE LOWER(CONCAT('%', :searchValue, '%')) " +
            "WHEN :fieldName = 'model' THEN LOWER(c.model) LIKE LOWER(CONCAT('%', :searchValue, '%')) " +
            "WHEN :fieldName = 'year' THEN CAST(c.year AS text) LIKE LOWER(CONCAT('%', :searchValue, '%')) " +
            "WHEN :fieldName = 'category' THEN LOWER(c.category) LIKE LOWER(CONCAT('%', :searchValue, '%')) " +
            "WHEN :fieldName = 'status' THEN LOWER(c.status) LIKE LOWER(CONCAT('%', :searchValue, '%')) " +
            "WHEN :fieldName = 'capacity' THEN CAST(c.capacity AS text) LIKE LOWER(CONCAT('%', :searchValue, '%')) " +
            "WHEN :fieldName = 'driver' THEN (LOWER(d.last_name) LIKE LOWER(CONCAT('%', :searchValue, '%')) OR LOWER(d.first_name) LIKE LOWER(CONCAT('%', :searchValue, '%')))"+
            "OR"+
            "(LOWER(d.last_name) LIKE LOWER(CONCAT('%', SPLIT_PART(:searchValue, ' ', -1), '%')) AND LOWER(d.first_name) LIKE LOWER(CONCAT('%', SPLIT_PART(:searchValue, ' ', 1), '%')))"+
            "END",
            countQuery = "SELECT COUNT(*) FROM cars c " +
                    "INNER JOIN drivers d ON d.id = c.driver_id " +
                    "INNER JOIN car_coordinates ON c.id = car_coordinates.car_id " +
                    "WHERE " +
                    "CASE " +
                    "WHEN :fieldName = 'brand' THEN LOWER(c.brand) LIKE LOWER(CONCAT('%', :searchValue, '%')) " +
                    "WHEN :fieldName = 'model' THEN LOWER(c.model) LIKE LOWER(CONCAT('%', :searchValue, '%')) " +
                    "WHEN :fieldName = 'year' THEN CAST(c.year AS text) LIKE LOWER(CONCAT('%', :searchValue, '%')) " +
                    "WHEN :fieldName = 'category' THEN LOWER(c.category) LIKE LOWER(CONCAT('%', :searchValue, '%')) " +
                    "WHEN :fieldName = 'status' THEN LOWER(c.status) LIKE LOWER(CONCAT('%', :searchValue, '%')) " +
                    "WHEN :fieldName = 'capacity' THEN CAST(c.capacity AS text) LIKE LOWER(CONCAT('%', :searchValue, '%')) " +
                    "WHEN :fieldName = 'driver' THEN (LOWER(d.last_name) LIKE LOWER(CONCAT('%', :searchValue, '%')) OR LOWER(d.first_name) LIKE LOWER(CONCAT('%', :searchValue, '%')))"+
                    "OR"+
                    "(LOWER(d.last_name) LIKE LOWER(CONCAT('%', SPLIT_PART(:searchValue, ' ', -1), '%')) AND LOWER(d.first_name) LIKE LOWER(CONCAT('%', SPLIT_PART(:searchValue, ' ', 1), '%')))"+
                    "END",
            nativeQuery = true)
    Page<Car> findByFieldContainingIgnoreCase(@Param("fieldName") String fieldName, @Param("searchValue") String searchValue, Pageable pageable);
}
