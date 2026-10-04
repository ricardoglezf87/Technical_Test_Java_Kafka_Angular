package com.technicaltest.order.processor.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.technicaltest.order.processor.model.Order;

@Service
public class OrderProcessedProducer {
    private static final String TOPIC = "order-processed";
    private final KafkaTemplate<String, Order> kafkaTemplate;

    public OrderProcessedProducer(KafkaTemplate<String, Order> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendProcessedOrder(Order order) {
        kafkaTemplate.send(TOPIC, order.getId().toString(), order);
    }
}