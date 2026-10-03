package com.technicaltest.order.processor.model;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor 
@AllArgsConstructor 
public class Order {
   
    private UUID id;    
    private String customer;
    private String product;
    private Integer quantity;
    private BigDecimal price;    
    private String status;
   
}