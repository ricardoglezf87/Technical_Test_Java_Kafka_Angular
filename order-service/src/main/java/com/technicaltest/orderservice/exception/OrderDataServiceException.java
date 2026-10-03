package com.technicaltest.orderservice.exception;

public class OrderDataServiceException extends RuntimeException {
    public OrderDataServiceException(String message) {
        super(message);
    }

    public OrderDataServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
