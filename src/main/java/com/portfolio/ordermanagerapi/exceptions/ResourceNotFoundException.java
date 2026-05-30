package com.portfolio.ordermanagerapi.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(Object id) {
        super("Resource not found ID" + id);
    }
}
