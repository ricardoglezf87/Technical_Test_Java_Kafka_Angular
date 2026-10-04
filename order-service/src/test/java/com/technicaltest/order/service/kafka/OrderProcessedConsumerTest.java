package com.technicaltest.order.service.kafka;

import static org.mockito.Mockito.verify;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.technicaltest.order.service.model.Order;
import com.technicaltest.order.service.service.OrderService;

@ExtendWith(MockitoExtension.class)
class OrderProcessedConsumerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderProcessedConsumer consumer;

    @Test
    void shouldUpdateStatusWhenProcessedOrderIsReceived() {

        UUID id = UUID.randomUUID();

        Order order = new Order();
        order.setId(id);
        order.setStatus("COMPLETED");

        consumer.consumeProcessedOrder(order);

        verify(orderService)
            .updateStatus(id, "COMPLETED");
    }
}