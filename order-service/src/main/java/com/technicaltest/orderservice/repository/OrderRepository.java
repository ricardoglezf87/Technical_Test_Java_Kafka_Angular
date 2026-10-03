package com.technicaltest.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.technicaltest.orderservice.model.Order;
import java.util.UUID;


public interface OrderRepository extends JpaRepository<Order, UUID> {
    
}