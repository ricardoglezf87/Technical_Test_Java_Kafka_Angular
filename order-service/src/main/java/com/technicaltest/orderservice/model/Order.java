package com.technicaltest.orderservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotBlank(message = "El cliente es obligatorio")
    private String customer;

    @NotBlank(message = "El producto es obligatorio")
    private String product;

    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    private Integer quantity;

    @DecimalMin(value = "0.01", message = "El precio debe ser mayor que 0.01")
    private BigDecimal price;
    
    private String status;
   
}
