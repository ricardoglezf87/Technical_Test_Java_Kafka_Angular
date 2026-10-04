package com.technicaltest.order.service.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.technicaltest.order.service.model.Order;
import com.technicaltest.order.service.service.OrderService;
import com.technicaltest.order.service.service.OrderSseService;

@Service 
public class OrderProcessedDltConsumer {

    private final OrderService orderService;
    private final OrderSseService orderSseService;

    public OrderProcessedDltConsumer(OrderService orderService, OrderSseService orderSseService) {
        this.orderService = orderService;
        this.orderSseService = orderSseService;
    }
    
    @KafkaListener (topics = "order-processed-dlt", groupId = "order-service-group")
    public void consume(Order processedOrder) {        
        Order order = orderService.updateStatus(processedOrder.getId(), "FAILED");
        orderSseService.send(order);
    }
}
