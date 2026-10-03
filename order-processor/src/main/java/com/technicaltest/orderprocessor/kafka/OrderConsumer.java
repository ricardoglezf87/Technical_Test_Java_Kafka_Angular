package com.technicaltest.orderprocessor.kafka;

import com.technicaltest.orderprocessor.model.Order;
import org.springframework.stereotype.Service;
import org.springframework.kafka.annotation.KafkaListener;

@Service
public class OrderConsumer {

    @KafkaListener(topics = "order-created", groupId = "order-processor-group")
    
    public void consumeOrder(Order order) {
        System.out.println("---------------------------------");
        System.out.println("PEDIDO RECIBIDO");
        System.out.println("ID: " + order.getId());
        System.out.println("Cliente: " + order.getCustomer());
        System.out.println("Producto: " + order.getProduct());
        System.out.println("Cantidad: " + order.getQuantity());
        System.out.println("Precio: " + order.getPrice());
        System.out.println("Estado: " + order.getStatus());
        System.out.println("---------------------------------");
    }

}
