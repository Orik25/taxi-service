package com.lightweight.taxiservice.controller.RESTcontroller;

import com.lightweight.taxiservice.entity.Car;
import com.lightweight.taxiservice.entity.Order;
import com.lightweight.taxiservice.service.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public List<Order> getOrders(){
        return orderService.findAll();
    }

    @GetMapping("/orders/{orderId}")
    public Order getOrderById(@PathVariable Long orderId){
        return orderService.findById(orderId);
    }

    @PostMapping("/orders")
    public Order addOrder(@RequestBody Order order){
        return orderService.save(order);
    }

    @PutMapping("/orders/{orderId}")
    public Order updateOrder(@PathVariable Long orderId, @RequestBody Order updatedOrder) {
        updatedOrder.setId(orderId);
        return orderService.update(updatedOrder);
    }

    @DeleteMapping("/orders/{orderId}")
    public void deleteOrder(@PathVariable Long orderId){
        Order deletedOrder = orderService.findById(orderId);
        orderService.deleteById(orderId);
    }
}
