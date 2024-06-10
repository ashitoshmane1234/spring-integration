package com.app.springintegration.springintegration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.handler.advice.ExpressionEvaluatingRequestHandlerAdvice;
import org.springframework.messaging.MessageChannel;

import com.app.springintegration.order.entity.Order;

@Configuration
@IntegrationComponentScan
public class IntegrationConfig {

    @Bean
    public MessageChannel orderChannel() {
        return MessageChannels.direct().getObject();
    }

    @Bean
    public MessageChannel errorChannel() {
        return MessageChannels.direct().getObject();
    }

    @Bean
    public IntegrationFlow orderFlow() {
        return IntegrationFlow.from("orderChannel")
                .handle("inventoryService", "checkStock", e -> e.advice(errorHandlingAdvice()))
                .filter(Boolean.class, isSuccess -> isSuccess)
                .handle("paymentService", "processPayment", e -> e.advice(errorHandlingAdvice()))
                .get();
    }

    @Bean
    public IntegrationFlow errorHandler() {
        return IntegrationFlow.from("errorChannel")
                .handle("errorHandler", "handleError")
                .get();
    }

    @Bean
    public ExpressionEvaluatingRequestHandlerAdvice errorHandlingAdvice() {
        ExpressionEvaluatingRequestHandlerAdvice advice = new ExpressionEvaluatingRequestHandlerAdvice();
        advice.setFailureChannel(errorChannel());
        advice.setTrapException(true);
        return advice;
    }

    @MessagingGateway(defaultRequestChannel = "orderChannel")
    public interface OrderGateway {
        void sendOrder(Order order);
    }
}