package com.lightweight.taxiservice.controller;


import com.lightweight.taxiservice.DAO.DriverRepository;
import com.lightweight.taxiservice.DTO.driver.DriverUpdateDTO;
import com.lightweight.taxiservice.DTO.order.ConverterOrderDTO;
import com.lightweight.taxiservice.DTO.order.CreatedOrderDTO;
import com.lightweight.taxiservice.DTO.order.OrderForUpdateDTO;
import com.lightweight.taxiservice.constant.OrderStatus;
import com.lightweight.taxiservice.entity.Order;
import com.lightweight.taxiservice.service.interfaces.DriverService;
import com.lightweight.taxiservice.service.interfaces.OrderService;
import com.lightweight.taxiservice.service.interfaces.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/system")
public class OrderController {
    private OrderService orderService;
    private ConverterOrderDTO converterOrder;

    private DriverService driverService;
    private UserService userService;

    @Autowired
    public OrderController(OrderService orderService,
                           ConverterOrderDTO converterOrder,
                           DriverService driverService,
                           UserService userService) {
        this.orderService = orderService;
        this.converterOrder = converterOrder;
        this.driverService = driverService;
        this.userService = userService;

    }

    @GetMapping("/orders")
    public String getActiveOrders(@RequestParam(name = "page", defaultValue = "0") int page,
                                  @RequestParam(name = "size", defaultValue = "1") int size,
                                  @RequestParam(name = "sortField", defaultValue = "id") String sortField,
                                  @RequestParam(name = "sortOrder", defaultValue = "asc") String sortOrder,
                                  Model model) {
        model.addAttribute("ordersPage", orderService.findActiveOrders(page, size, sortField, sortOrder));
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortOrder", sortOrder);
        return "admin/list-orders";
    }

    @GetMapping("/search-orders")
    public String searchOrdersByField(@RequestParam(name = "searchField") String searchField,
                                      @RequestParam(name = "searchValue") String searchValue,
                                      @RequestParam(name = "page", defaultValue = "0") int page,
                                      @RequestParam(name = "size", defaultValue = "1") int size,
                                      Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Order> ordersPage = orderService.findByFieldContainingIgnoreCaseActive(searchField, searchValue, pageable);
        model.addAttribute("searchField", searchField);
        model.addAttribute("searchValue", searchValue);
        model.addAttribute("ordersPage", ordersPage);
        return "admin/list-orders";
    }

    @GetMapping("/archive-order/{id}")
    public String archiveOrder(@PathVariable Long id) {
        Order order = orderService.findById(id);
        order.setStatus(OrderStatus.ARCHIVED.getStatus());
        orderService.save(order);
        return "redirect:/system/orders";
    }

    @GetMapping("/update-order/{id}")
    public String showUpdateOrderForm(@PathVariable Long id, Model model) {
        OrderForUpdateDTO order = converterOrder.convertToDTO(orderService.findById(id));
        model.addAttribute("order", order);
        model.addAttribute("availableUsers", userService.findAvailableForOrderUsers());
        model.addAttribute("availableDrivers", driverService.findAvailableForOrderDrivers());

        return "admin/forms/update-order";
    }

    @PostMapping("/update-order/{id}")
    public String updateOrder(@PathVariable Long id,
                               @Valid @ModelAttribute("order") OrderForUpdateDTO order,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("order", order);
            model.addAttribute("availableUsers", userService.findAvailableForOrderUsers());
            model.addAttribute("availableDrivers", driverService.findAvailableForOrderDrivers());
            return "admin/forms/update-order";
        }

        orderService.update(id, order);
        return "redirect:/system/orders";
    }

    @GetMapping("/add-order")
    public String showAddOrderForm(Model model) {
        CreatedOrderDTO order = new CreatedOrderDTO();
        model.addAttribute("order", order);
        model.addAttribute("availableUsers", userService.findAvailableForOrderUsers());
        model.addAttribute("availableDrivers", driverService.findAvailableForOrderDrivers());

        return "admin/forms/add-order";
    }
    @PostMapping("/add-order")
    public String addOrder(@Valid @ModelAttribute("order") CreatedOrderDTO order,
                              BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("order", order);
            model.addAttribute("availableUsers", userService.findAvailableForOrderUsers());
            model.addAttribute("availableDrivers", driverService.findAvailableForOrderDrivers());
            return "admin/forms/add-order";
        }
        Order newOrder = converterOrder.convertToEntity(order);
        orderService.save(newOrder);
        return "redirect:/system/orders";
    }

    @GetMapping("/delete-order/{id}")
    public String delete(@PathVariable("id") Long id) {
        orderService.deleteById(id);
        return "redirect:/system/orders";
    }
}
