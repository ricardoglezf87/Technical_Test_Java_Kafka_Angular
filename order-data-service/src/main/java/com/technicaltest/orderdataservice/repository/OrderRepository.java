package com.technicaltest.orderdataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.technicaltest.orderdataservice.model.Order;
import java.util.UUID;


public interface OrderRepository extends JpaRepository<Order, UUID> {
    
}