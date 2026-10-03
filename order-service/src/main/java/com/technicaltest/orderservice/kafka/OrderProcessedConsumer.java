package com.technicaltest.orderservice.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.technicaltest.orderservice.model.Order;
import com.technicaltest.orderservice.repository.OrderRepository;

@Service 
public class OrderProcessedConsumer {
    private final OrderProducer orderProducer;
    private final OrderRepository orderRepository;

    public OrderProcessedConsumer(OrderRepository orderRepository, OrderProducer orderProducer) {
        this.orderRepository = orderRepository;
        this.orderProducer = orderProducer;
    }

    @KafkaListener (topics = "order-processed", groupId = "order-service-group")
    public void consumeProcessedOrder(Order processedOrder) {
        orderRepository.findById(processedOrder.getId())
            .ifPresent(order -> {
            order.setStatus(processedOrder.getStatus());
            orderRepository.save(order);
        });
    }
}
