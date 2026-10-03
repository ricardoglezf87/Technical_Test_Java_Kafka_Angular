package com.technicaltest.order.service.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.technicaltest.order.service.model.Order;
import com.technicaltest.order.service.service.OrderService;

@Service 
public class OrderProcessedConsumer {
    private final OrderService orderService;

    public OrderProcessedConsumer(OrderService orderService) {
        this.orderService = orderService;
    }

    @KafkaListener (topics = "order-processed", groupId = "order-service-group")
    public void consumeProcessedOrder(Order processedOrder) {
        orderService.updateStatus(processedOrder.getId(), processedOrder.getStatus());
    }
}
