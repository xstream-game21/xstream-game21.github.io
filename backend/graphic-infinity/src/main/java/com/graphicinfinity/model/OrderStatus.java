package com.graphicinfinity.model;

public enum OrderStatus {
    PENDING("pending"),
    CONFIRMED("confirmed"),
    SHIPPED("shipped"),
    DELIVERED("delivered"),
    CANCELLED("cancelled");

    private final String value;

    OrderStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}