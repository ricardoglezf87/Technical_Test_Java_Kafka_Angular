package com.technicaltest.order.service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.technicaltest.order.service.model.Order;
import com.technicaltest.order.service.service.OrderService;

import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

@Tag(
    name = "Orders",
    description = "Operaciones para la gestión de pedidos"
)
@RequestMapping("/api/orders")
@RestController 
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(
    summary = "Crear pedido",
    description = "Crea un pedido y lo envía para su procesamiento mediante Kafka"
    )   
    @PostMapping 
    @ResponseStatus (HttpStatus.CREATED)
    public Order createOrder(@Valid  @RequestBody Order order) {
        return orderService.create(order);
    }

    @Operation(
    summary = "Obtener todos los pedidos",
    description = "Devuelve el listado completo de pedidos"
    )
    @GetMapping
    public List<Order> getOrders() {
        return orderService.getAll();
    }

   
}