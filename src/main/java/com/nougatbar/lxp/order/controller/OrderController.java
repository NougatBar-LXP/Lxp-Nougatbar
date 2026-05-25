package com.nougatbar.lxp.order.controller;

import com.nougatbar.lxp.order.dto.response.OrderResponse;
import com.nougatbar.lxp.order.service.OrderService;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class OrderController {

    private static final Long TEMP_MEMBER_ID = 1L;

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/orders")
    public ResponseEntity<OrderResponse> createOrder() {
        OrderResponse response = orderService.createOrder(TEMP_MEMBER_ID);

        return ResponseEntity.created(URI.create("/orders/" + response.orderId())).body(response);
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponse>> getOrders() {
        List<OrderResponse> orders = orderService.getOrders(TEMP_MEMBER_ID);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long orderId) {
        OrderResponse order = orderService.getOrderById(TEMP_MEMBER_ID, orderId);
        return ResponseEntity.ok(order);
    }
}
