package com.app.springintegration.order.dto;

import lombok.Getter;

@Getter
public class OrderDto {

    Long userId;
    Long productId;
    Integer quantity;
    Double totalAmount;
    Boolean isPriority;
}
