package com.technicaltest.orderdataservice.service;

import com.technicaltest.orderdataservice.model.Order;
import com.technicaltest.orderdataservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Optional<Order> findById(UUID id) {
        return orderRepository.findById(id);
    }

    public void deleteById(UUID id) {
        orderRepository.deleteById(id);
    }

    public Order update(UUID id, Order updatedOrder) {
        return orderRepository.findById(id)
                .map(order -> {
                    order.setCustomer(updatedOrder.getCustomer());
                    order.setProduct(updatedOrder.getProduct());
                    order.setQuantity(updatedOrder.getQuantity());
                    order.setPrice(updatedOrder.getPrice());
                    order.setStatus(updatedOrder.getStatus());

                    return orderRepository.save(order);
                })
                .orElseThrow(() -> new RuntimeException("Order not found with id " + id));
    }

    public Order updateStatus(UUID id, String status) {
        return orderRepository.findById(id)
                .map(order -> {
                    order.setStatus(status);
                    return orderRepository.save(order);
                })
                .orElseThrow(() -> new RuntimeException("Order not found with id " + id));
    }
}
