package io.viana.order_service.controller;

import io.viana.order_service.model.OrderResponse;
import io.viana.order_service.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public OrderResponse createOrder() {
        return service.createOrder();
    }

    @GetMapping("/fail")
    public OrderResponse failOrder() {
        return service.simulateFailure();
    }
}