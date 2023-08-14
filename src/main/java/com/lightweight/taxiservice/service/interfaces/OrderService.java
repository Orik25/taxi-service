package com.lightweight.taxiservice.service.interfaces;

import com.lightweight.taxiservice.entity.Car;
import com.lightweight.taxiservice.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAll();
    Order findById(Long id);
    Order save(Order order);
    Order update(Order order);
    void deleteById(Long id);
}
