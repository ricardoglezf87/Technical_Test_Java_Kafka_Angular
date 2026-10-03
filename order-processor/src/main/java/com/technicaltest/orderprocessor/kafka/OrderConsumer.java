package com.technicaltest.orderprocessor.kafka;

import com.technicaltest.orderprocessor.model.Order;

import org.springframework.stereotype.Service;
import org.springframework.kafka.annotation.KafkaListener;

@Service
public class OrderConsumer {

    private final OrderProcessedProducer orderProcessedProducer;

    public OrderConsumer(OrderProcessedProducer orderProcessedProducer) {
        this.orderProcessedProducer = orderProcessedProducer;
    }
    
    @KafkaListener(topics = "order-created", groupId = "order-processor-group")   
    public void consumeOrder(Order order) {
        System.out.println("Order consumed from Kafka: " + order.getId());
        order.setStatus("COMPLETED");
        orderProcessedProducer.sendProcessedOrder(order);
        System.out.println("Order processed and sent to Kafka: " + order.getId());
    }

}
