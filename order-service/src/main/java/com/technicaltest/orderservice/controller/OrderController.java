package com.technicaltest.orderservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.technicaltest.orderservice.model.Order;
import com.technicaltest.orderservice.service.OrderService;

import jakarta.validation.Valid;

@RequestMapping("/api/orders")
@RestController 
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping 
    @ResponseStatus (HttpStatus.CREATED)
    public Order createOrder(@Valid  @RequestBody Order order) {
        return orderService.create(order);
    }

    @GetMapping
    public List<Order> getOrders() {
        return orderService.getAll();
    }

    @GetMapping("/test")
    public String test() {
        return "OrderController funcionando";
    }
    
}