package com.technicaltest.order.service.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.technicaltest.order.service.client.OrderDataClient;
import com.technicaltest.order.service.kafka.OrderProducer;
import com.technicaltest.order.service.model.Order;

@Service 
public class OrderService {
        private final OrderDataClient orderDataClient;
        private final OrderProducer orderProducer;

    public OrderService(OrderDataClient orderDataClient, OrderProducer orderProducer) {
        this.orderDataClient = orderDataClient;
        this.orderProducer = orderProducer;
    }

    public Order create(Order order) {
        order.setId(UUID.randomUUID());
        order.setStatus("CREATED");
        Order savedOrder = orderDataClient.save(order);
        orderProducer.sendOrder(savedOrder);
        return savedOrder;
    }

    public Order updateStatus(UUID orderId, String status) {
        return orderDataClient.updateStatus(orderId.toString(), status);
    }

    public List<Order> getAll() {
        return orderDataClient.getAll();
    }
    
}
