package com.technicaltest.order.data.service.service;

import org.springframework.stereotype.Service;

import com.technicaltest.order.data.service.exception.OrderNotFoundException;
import com.technicaltest.order.data.service.model.Order;
import com.technicaltest.order.data.service.repository.OrderRepository;

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

    public Order updateStatus(UUID id, String status) {
        return orderRepository.findById(id)
                .map(order -> {
                    order.setStatus(status);
                    return orderRepository.save(order);
                })
                .orElseThrow(() -> new OrderNotFoundException(id));
    }
}
