package com.technicaltest.order.service.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.technicaltest.order.service.client.OrderDataClient;
import com.technicaltest.order.service.kafka.OrderProducer;
import com.technicaltest.order.service.model.Order;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderDataClient orderDataClient;

    @Mock
    private OrderProducer orderProducer;

    @InjectMocks
    private OrderService orderService;

    @Test
    void shouldCreateOrder() {

        Order order = new Order();
        order.setCustomer("Ricardo");
        order.setProduct("Portatil");
        order.setQuantity(1);
        order.setPrice(new BigDecimal("1000"));

        when(orderDataClient.save(any(Order.class)))
            .thenAnswer(invocation -> invocation.getArgument(0));

        Order result = orderService.create(order);

        assertNotNull(result.getId());
        assertEquals("CREATED", result.getStatus());
        assertEquals("Ricardo", result.getCustomer());

        verify(orderDataClient).save(order);
        verify(orderProducer).sendOrder(order);
    }

    @Test
    void shouldGetAllOrders() {

        Order order = new Order();
        order.setCustomer("Ricardo");
        order.setProduct("Portatil");

        when(orderDataClient.getAll())
            .thenReturn(java.util.List.of(order));

        var result = orderService.getAll();

        assertEquals(1, result.size());
        assertEquals("Ricardo", result.get(0).getCustomer());

        verify(orderDataClient).getAll();
    }

    @Test
    void shouldUpdateStatus() {

        Order order = new Order();
        order.setStatus("COMPLETED");

        when(orderDataClient.updateStatus(any(), any()))
            .thenReturn(order);

        Order result = orderService.updateStatus(
            java.util.UUID.randomUUID(),
            "COMPLETED"
        );

        assertEquals("COMPLETED", result.getStatus());

        verify(orderDataClient)
            .updateStatus(any(), any());
    }
}