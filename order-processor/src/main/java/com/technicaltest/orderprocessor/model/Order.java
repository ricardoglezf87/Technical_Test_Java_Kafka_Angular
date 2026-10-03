package com.technicaltest.orderprocessor.model;

import java.math.BigDecimal;
import java.util.UUID;

public class Order {
    private UUID id;
    private String customer;
    private String product;
    private Integer quantity;
    private BigDecimal price;
    private String status;

    public Order() {
    }   

    public Order(UUID id, String customer, String product, Integer quantity, BigDecimal price, String status) {
        this.id = id;
        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
