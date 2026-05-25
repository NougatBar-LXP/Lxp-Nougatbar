package com.nougatbar.lxp.order.controller;

import com.nougatbar.lxp.cart.dto.response.CartResponse;
import com.nougatbar.lxp.cart.service.CartService;
import com.nougatbar.lxp.order.dto.response.OrderResponse;
import com.nougatbar.lxp.order.service.OrderService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order-ui")
public class OrderPageController {

    private static final Long TEMP_MEMBER_ID = 1L;

    private final OrderService orderService;
    private final CartService cartService;

    public OrderPageController(OrderService orderService, CartService cartService) {
        this.orderService = orderService;
        this.cartService = cartService;
    }

    @GetMapping
    public String showOrders(Model model) {
        List<OrderResponse> orders = orderService.getOrders(TEMP_MEMBER_ID);
        addOrderModel(model, orders, orders.isEmpty() ? null : orders.get(0));
        return "order/index";
    }

    @GetMapping("/{orderId}")
    public String showOrder(@PathVariable Long orderId, Model model) {
        List<OrderResponse> orders = orderService.getOrders(TEMP_MEMBER_ID);
        OrderResponse selectedOrder = orderService.getOrderById(TEMP_MEMBER_ID, orderId);
        addOrderModel(model, orders, selectedOrder);
        return "order/index";
    }

    @PostMapping
    public String createOrder() {
        List<CartResponse> carts = cartService.findCartsById(TEMP_MEMBER_ID);
        OrderResponse order = orderService.createOrder(TEMP_MEMBER_ID, carts);
        return "redirect:/order-ui/" + order.orderId();
    }

    private void addOrderModel(Model model,
                               List<OrderResponse> orders,
                               OrderResponse selectedOrder) {
        model.addAttribute("memberId", TEMP_MEMBER_ID);
        model.addAttribute("orders", orders);
        model.addAttribute("selectedOrder", selectedOrder);
        model.addAttribute("orderCount", orders.size());
    }
}
