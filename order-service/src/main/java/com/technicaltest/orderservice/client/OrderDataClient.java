package com.technicaltest.orderservice.client;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.technicaltest.orderservice.model.Order;

@Component 
public class OrderDataClient {
    private final RestClient restClient;

    public OrderDataClient() {
        this.restClient = RestClient.builder().baseUrl("http://localhost:8081/data/orders").build();
    }

    public Order save(Order order) {
        return restClient.post()
        .body(order)
        .retrieve()
        .body(Order.class);
    }

    public List<Order> getAll() {
        Order[] orders = restClient.get()
            .retrieve()
            .body(Order[].class);

        return orders == null ? List.of() : Arrays.asList(orders);
    }

    public Order getById(String id) {
        return restClient.get()
            .uri("/{id}", id)
            .retrieve()
            .body(Order.class);
    }

    public Order updateStatus(String id, String status) {
        return restClient.patch()
            .uri(uriBuilder -> uriBuilder
            .path("/{id}/status")
            .queryParam("status", status)
            .build(id))
            .retrieve()
            .body(Order.class);
    }

}
