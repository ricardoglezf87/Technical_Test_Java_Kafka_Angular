package com.technicaltest.orderservice.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.technicaltest.orderservice.kafka.OrderProducer;
import com.technicaltest.orderservice.model.Order;

@RequestMapping("/api/orders")
@RestController 
public class OrderController {
    private final OrderProducer orderProducer;

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @PostMapping 
    @ResponseStatus (HttpStatus.CREATED)
    public Order createOrder(@RequestBody Order order) {
        order.setId(UUID.randomUUID());
        order.setStatus("CREATED");
        orderProducer.sendOrder(order);
        return order;
    }

    @GetMapping("/test")
    public String test() {
        return "OrderController funcionando";
    }
    
}