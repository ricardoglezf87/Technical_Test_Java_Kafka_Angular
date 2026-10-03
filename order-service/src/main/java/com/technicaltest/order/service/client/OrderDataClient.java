package com.technicaltest.order.service.client;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.technicaltest.order.service.exception.OrderDataServiceException;
import com.technicaltest.order.service.model.Order;

@Component 
public class OrderDataClient {

    private final RestClient restClient;

    public OrderDataClient( @Value("${order-data-service.url}") String baseUrl) {
        System.out.println("baseUrl = " + baseUrl);
        this.restClient = RestClient.builder().baseUrl(baseUrl).build();
    }

    public Order save(Order order) {
        try{
            return restClient.post()
            .body(order)
            .retrieve()
            .body(Order.class);
        }
        catch (Exception e) {
            throw new OrderDataServiceException("Error guardando el pedido", e);
        }
    }

    public List<Order> getAll() {
        try{
            Order[] orders = restClient.get()
                .retrieve()
                .body(Order[].class);

            return orders == null ? List.of() : Arrays.asList(orders);
        }
        catch (Exception e) {
                throw new OrderDataServiceException("Error obteniendo los pedidos", e);
        }
    }

    public Order getById(String id) {
        try{
            return restClient.get()
            .uri("/{id}", id)
            .retrieve()
            .body(Order.class);
        }
        catch (Exception e) {
                throw new OrderDataServiceException("Error obteniendo el pedido con id: " + id, e);
        }
    }

    public Order updateStatus(String id, String status) {
        try{
            return restClient.patch()
            .uri(uriBuilder -> uriBuilder
            .path("/{id}/status")
            .queryParam("status", status)
            .build(id))
            .retrieve()
            .body(Order.class);
        }
        catch (Exception e) {
                throw new OrderDataServiceException("Error actualizando el estado del pedido", e);
        }
    }

}
