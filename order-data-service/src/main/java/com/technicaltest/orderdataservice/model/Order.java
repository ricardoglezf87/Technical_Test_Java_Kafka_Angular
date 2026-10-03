package com.technicaltest.orderdataservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor 
@AllArgsConstructor 
@Entity
@Table(name = "orders")
public class Order {
   
    @Id
    private UUID id;
    private String customer;
    private String product;
    private Integer quantity;
    private BigDecimal price;   
    private String status;
   
}
