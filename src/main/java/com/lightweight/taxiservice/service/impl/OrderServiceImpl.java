package com.lightweight.taxiservice.service.impl;

import com.lightweight.taxiservice.DAO.OrderRepository;
import com.lightweight.taxiservice.entity.Order;
import com.lightweight.taxiservice.exception.NoOrderFoundException;
import com.lightweight.taxiservice.exception.NoOrdersFoundException;
import com.lightweight.taxiservice.service.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
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
    public Order update(Order order) {
        isDatabaseEmpty();
        Long id = order.getId();
        orderRepository.findById(id)
                .orElseThrow(() -> new NoOrderFoundException("Impossible to update the Order. Order not found with id: " + id));
        return orderRepository.save(order);
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

    private void isDatabaseEmpty() {
        orderRepository.findFirstByOrderByIdAsc()
                .orElseThrow(() -> new NoOrderFoundException("Data base has not any records of orders"));
    }
}
