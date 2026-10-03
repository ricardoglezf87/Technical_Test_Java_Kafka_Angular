package com.technicaltest.order.service.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.technicaltest.order.service.model.Order;
import com.technicaltest.order.service.service.OrderService;

@Service 
public class OrderProcessedDltConsumer {

    private final OrderService orderService;

    public OrderProcessedDltConsumer(OrderService orderService) {
        this.orderService = orderService;
    }
    
    @KafkaListener (topics = "order-processed-dlt", groupId = "order-service-group")
    public void consume(Order processedOrder) {        
        orderService.updateStatus(processedOrder.getId(), "FAILED");
    }
}
