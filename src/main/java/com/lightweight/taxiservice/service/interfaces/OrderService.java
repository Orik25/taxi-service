package com.lightweight.taxiservice.service.interfaces;

import com.lightweight.taxiservice.DTO.order.OrderForUpdateDTO;
import com.lightweight.taxiservice.entity.Car;
import com.lightweight.taxiservice.entity.Driver;
import com.lightweight.taxiservice.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {
    List<Order> findAll();
    Page<Order> findActiveOrders(int page, int size, String sortField, String sortOrder);
    Page<Order> findArchivedOrders(int page, int size, String sortField, String sortOrder);
    Order findById(Long id);
    Order save(Order order);
    Order update(Long id, OrderForUpdateDTO order);
    void deleteById(Long id);

    Page<Order> findByFieldContainingIgnoreCaseActive(String fieldName,String searchValue, Pageable pageable);
}
