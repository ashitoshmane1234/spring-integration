package com.app.springintegration.order.service;

import org.springframework.stereotype.Service;

import com.app.springintegration.order.dto.OrderDto;
import com.app.springintegration.order.entity.Order;
import com.app.springintegration.order.repo.OrderRepository;
import com.app.springintegration.springintegration.IntegrationConfig.OrderGateway;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderGateway orderGateway;

    private final OrderRepository orderRepository;

    public Order createOrder(OrderDto orderDto) {

        Order newOrder = new Order();
        newOrder.setStatus("PENDING");
        newOrder.setIsPriority(orderDto.getIsPriority());
        newOrder.setProductId(orderDto.getProductId());
        newOrder.setUserId(orderDto.getUserId());
        newOrder.setQuantity(orderDto.getQuantity());
        newOrder.setTotalAmount(orderDto.getTotalAmount());

        Order createdOrder = orderRepository.save(newOrder);
        orderGateway.sendOrder(createdOrder);
        return createdOrder;
    }
}