package com.lightweight.taxiservice.DAO;

import com.lightweight.taxiservice.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car,Long> {
    List<Car> findCarsByDriverIdIsNull();
}
