package com.lightweight.taxiservice.DAO;

import com.lightweight.taxiservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findFirstByOrderByIdAsc();

    Optional<Order> findLastByOrderByIdAsc();

}
