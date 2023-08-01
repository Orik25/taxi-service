package com.lightweight.taxiservice.DAO;

import com.lightweight.taxiservice.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver,Long> {

}
