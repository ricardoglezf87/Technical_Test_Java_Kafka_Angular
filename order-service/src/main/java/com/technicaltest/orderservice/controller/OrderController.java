package com.technicaltest.orderservice.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.technicaltest.orderservice.kafka.OrderProducer;
import com.technicaltest.orderservice.model.Order;
import com.technicaltest.orderservice.repository.OrderRepository;

@RequestMapping("/api/orders")
@RestController 
public class OrderController {
    private final OrderProducer orderProducer;
    private final OrderRepository orderRepository;

    public OrderController(OrderProducer orderProducer, OrderRepository orderRepository) {
        this.orderProducer = orderProducer;
        this.orderRepository = orderRepository; 
    }

    @PostMapping 
    @ResponseStatus (HttpStatus.CREATED)
    public Order createOrder(@RequestBody Order order) {
        order.setId(UUID.randomUUID());
        order.setStatus("CREATED");
        orderRepository.save(order);
        orderProducer.sendOrder(order);
        return order;
    }

    @GetMapping
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/test")
    public String test() {
        return "OrderController funcionando";
    }
    
}