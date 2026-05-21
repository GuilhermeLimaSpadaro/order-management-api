package com.portfolio.ordermanagerapi.model.enums;

import java.util.Arrays;

public enum OrderStatus {
    WAITING_PAYMENT(1),
    PAID(2),
    SHIPPED(3),
    DELIVERED(4),
    CANCELED(5);

    private final int code;

    OrderStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static OrderStatus valueOf(int code) {
        return Arrays.stream(OrderStatus.values())
                .filter(orderStatus -> orderStatus.getCode() == code)
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Codigo invalido!"));
    }
}
