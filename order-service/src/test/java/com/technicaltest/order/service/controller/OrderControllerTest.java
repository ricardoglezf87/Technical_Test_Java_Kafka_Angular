package com.technicaltest.order.service.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import tools.jackson.databind.ObjectMapper;

import com.technicaltest.order.service.model.Order;
import com.technicaltest.order.service.service.OrderService;

@WebMvcTest (OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private OrderService orderService;

    @Test
    void shouldCreateOrder() throws Exception {

        Order order = new Order();
        order.setId(UUID.randomUUID());
        order.setCustomer("Ricardo");
        order.setProduct("Portatil");
        order.setQuantity(1);
        order.setPrice(new BigDecimal("1000"));
        order.setStatus("CREATED");

        when(orderService.create(any(Order.class)))
            .thenReturn(order);

        mockMvc.perform(post("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(order)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.customer").value("Ricardo"))
            .andExpect(jsonPath("$.product").value("Portatil"))
            .andExpect(jsonPath("$.status").value("CREATED"));
    }

    @Test
    void shouldGetAllOrders() throws Exception {

        Order order = new Order();
        order.setId(UUID.randomUUID());
        order.setCustomer("Ricardo");
        order.setProduct("Portatil");
        order.setQuantity(1);
        order.setPrice(new BigDecimal("1000"));
        order.setStatus("COMPLETED");

        when(orderService.getAll())
            .thenReturn(List.of(order));

        mockMvc.perform(get("/api/orders"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].customer").value("Ricardo"))
            .andExpect(jsonPath("$[0].product").value("Portatil"))
            .andExpect(jsonPath("$[0].status").value("COMPLETED"));
    }
}