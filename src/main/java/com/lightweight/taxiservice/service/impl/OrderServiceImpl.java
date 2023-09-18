package com.lightweight.taxiservice.service.impl;

import com.lightweight.taxiservice.DAO.OrderRepository;
import com.lightweight.taxiservice.DTO.order.ConverterOrderDTO;
import com.lightweight.taxiservice.DTO.order.OrderForUpdateDTO;
import com.lightweight.taxiservice.constant.OrderStatus;
import com.lightweight.taxiservice.entity.Order;
import com.lightweight.taxiservice.exception.NoOrderFoundException;
import com.lightweight.taxiservice.exception.NoOrdersFoundException;
import com.lightweight.taxiservice.service.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private ConverterOrderDTO converterOrderDTO;
    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            ConverterOrderDTO converterOrderDTO) {
        this.orderRepository = orderRepository;
        this.converterOrderDTO = converterOrderDTO;
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders = orderRepository.findAll();
        if (!orders.isEmpty()) {
            return orders;
        } else {
            throw new NoOrdersFoundException("There are no orders exist");
        }
    }

    @Override
    public Page<Order> findActiveOrders(int page, int size, String sortField, String sortOrder) {
        isDatabaseEmpty();
        Sort sort = Sort.by(sortField);

        if ("desc".equals(sortOrder)) {
            sort = sort.descending();
        }
        Pageable pageable = PageRequest.of(page,size,sort);
        return orderRepository.findActiveOrders(pageable);
    }

    @Override
    public Page<Order> findArchivedOrders(int page, int size, String sortField, String sortOrder) {
        isDatabaseEmpty();
        Sort sort = Sort.by(sortField);

        if ("desc".equals(sortOrder)) {
            sort = sort.descending();
        }
        Pageable pageable = PageRequest.of(page,size,sort);
        return orderRepository.findArchivedOrders(pageable);
    }

    @Override
    public Order findById(Long id) {
        isDatabaseEmpty();
        return orderRepository.findById(id)
                .orElseThrow(() -> new NoOrderFoundException("Order not found with id: " + id));
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order update(Long id, OrderForUpdateDTO orderForUpdateDTO) {
        isDatabaseEmpty();
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NoOrderFoundException("Impossible to update the Order. Order not found with id: " + id));
        Order updatedOrder = converterOrderDTO.convertToEntity(orderForUpdateDTO, order);
        return orderRepository.save(updatedOrder);
    }

    @Override
    public void deleteById(Long id) {
        isDatabaseEmpty();
        orderRepository.findById(id)
                .orElseThrow(() -> new NoOrderFoundException("Impossible to delete the Order." +
                        " Order not found with id: " + id));

        orderRepository.deleteById(id);
        orderRepository.flush();
    }

    @Override
    public Page<Order> findByFieldContainingIgnoreCaseActive(String fieldName, String searchValue, Pageable pageable) {
        return orderRepository.findByFieldContainingIgnoreCase(fieldName, searchValue, OrderStatus.ACTIVE.getStatus(), pageable);
    }

    private void isDatabaseEmpty() {
        orderRepository.findFirstByOrderByIdAsc()
                .orElseThrow(() -> new NoOrderFoundException("Data base has not any records of orders"));
    }
}
