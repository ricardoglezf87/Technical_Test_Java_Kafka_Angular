package com.technicaltest.order.data.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.technicaltest.order.data.service.model.Order;

import java.util.UUID;


public interface OrderRepository extends JpaRepository<Order, UUID> {
    
}