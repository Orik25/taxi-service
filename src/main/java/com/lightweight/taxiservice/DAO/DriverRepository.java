package com.lightweight.taxiservice.DAO;

import com.lightweight.taxiservice.entity.Driver;
import com.lightweight.taxiservice.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver, Long> {
    Optional<Driver> findFirstByOrderByIdAsc();

    Optional<Driver> findByEmail(String email);

    @Query(value = "SELECT drivers.id, drivers.first_name, drivers.last_name,drivers.phone,drivers.email FROM drivers\n" +
            "LEFT JOIN cars ON drivers.id = cars.driver_id\n" +
            "WHERE cars.driver_id IS NULL", nativeQuery = true)
    List<Driver> findDriversWithoutCars();

    @Query(value = "SELECT d.*, c.model AS car_model, c.brand AS car_brand " +
            "FROM drivers d " +
            "INNER JOIN cars c ON d.id = c.driver_id " +
            "WHERE " +
            "CASE " +
            "WHEN :fieldName = 'first_name' THEN LOWER(d.first_name) LIKE LOWER(CONCAT('%', :searchValue, '%')) " +
            "WHEN :fieldName = 'last_name' THEN LOWER(d.last_name) LIKE LOWER(CONCAT('%', :searchValue, '%')) " +
            "WHEN :fieldName = 'phone' THEN LOWER(d.phone) LIKE LOWER(CONCAT('%', :searchValue, '%')) " +
            "WHEN :fieldName = 'email' THEN LOWER(d.email) LIKE LOWER(CONCAT('%', :searchValue, '%')) " +
            "WHEN :fieldName = 'car' THEN (LOWER(c.model) LIKE LOWER(CONCAT('%', :searchValue, '%')) OR LOWER(c.brand) LIKE LOWER(CONCAT('%', :searchValue, '%')))"+
            "OR"+
            "(LOWER(c.model) LIKE LOWER(CONCAT('%', SPLIT_PART(:searchValue, ' ', -1), '%')) AND LOWER(c.brand) LIKE LOWER(CONCAT('%', SPLIT_PART(:searchValue, ' ', 1), '%')))"+
            "END",
            countQuery = "SELECT COUNT(*) FROM drivers d " +
                    "INNER JOIN cars c ON d.id = c.driver_id " +
                    "WHERE " +
                    "CASE " +
                    "WHEN :fieldName = 'first_name' THEN LOWER(d.first_name) LIKE LOWER(CONCAT('%', :searchValue, '%')) " +
                    "WHEN :fieldName = 'last_name' THEN LOWER(d.last_name) LIKE LOWER(CONCAT('%', :searchValue, '%')) " +
                    "WHEN :fieldName = 'phone' THEN LOWER(d.phone) LIKE LOWER(CONCAT('%', :searchValue, '%')) " +
                    "WHEN :fieldName = 'email' THEN LOWER(d.email) LIKE LOWER(CONCAT('%', :searchValue, '%')) " +
                    "WHEN :fieldName = 'car' THEN (LOWER(c.model) LIKE LOWER(CONCAT('%', :searchValue, '%')) OR LOWER(c.brand) LIKE LOWER(CONCAT('%', :searchValue, '%')))"+
                    "OR"+
                    "(LOWER(c.model) LIKE LOWER(CONCAT('%', SPLIT_PART(:searchValue, ' ', -1), '%')) AND LOWER(c.brand) LIKE LOWER(CONCAT('%', SPLIT_PART(:searchValue, ' ', 1), '%')))"+
                    "END",
            nativeQuery = true)
    Page<Driver> findByFieldContainingIgnoreCase(@Param("fieldName") String fieldName, @Param("searchValue") String searchValue, Pageable pageable);

}
