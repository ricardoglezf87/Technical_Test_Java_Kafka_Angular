package com.technicaltest.orderprocessor.kafka;

import com.technicaltest.orderprocessor.model.Order;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

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