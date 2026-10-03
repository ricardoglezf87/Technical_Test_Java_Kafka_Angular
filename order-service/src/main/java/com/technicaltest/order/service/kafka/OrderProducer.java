
package com.technicaltest.order.service.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.technicaltest.order.service.model.Order;

@Service 
public class OrderProducer {

    private static final String TOPIC = "order-created";
    private final KafkaTemplate<String, Order> kafkaTemplate;

    public OrderProducer(KafkaTemplate<String, Order> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrder(Order order) {
        kafkaTemplate.send(TOPIC, order.getId().toString(), order);

        System.out.println("Order sent to Kafka: " + order.getId());
    }

}
