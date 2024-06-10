package com.app.springintegration.order.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);

    public void processPayment(Boolean isSuccess) {
        logger.info("Processing payment with success status: {}", isSuccess);

    }
}
