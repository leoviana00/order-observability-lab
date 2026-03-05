package io.viana.order_service.service;

import io.viana.order_service.model.OrderResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    public OrderResponse createOrder() {

        String orderId = UUID.randomUUID().toString();

        log.info("Order created",
                kv("event.type", "business"),
                kv("event.name", "order_created"),
                kv("order.id", orderId));

        return new OrderResponse(orderId, "CREATED");
    }

    public OrderResponse simulateFailure() {

        log.error("Simulated technical error",
                kv("event.type", "technical"),
                kv("event.name", "order_processing_error"));

        throw new RuntimeException("Simulated error");
    }

    private Object kv(String key, Object value) {
        return new Object() {
            public String toString() {
                return key + "=" + value;
            }
        };
    }
}