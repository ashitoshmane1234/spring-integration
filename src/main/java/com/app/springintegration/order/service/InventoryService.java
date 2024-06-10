package com.app.springintegration.order.service;

import org.springframework.stereotype.Service;

import com.app.springintegration.order.entity.Order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class InventoryService {
    private static final Logger logger = LoggerFactory.getLogger(InventoryService.class);

    public Boolean checkStock(Order order) {
        logger.info("Checking stock for order: {}", order);

        return true;
    }
}
