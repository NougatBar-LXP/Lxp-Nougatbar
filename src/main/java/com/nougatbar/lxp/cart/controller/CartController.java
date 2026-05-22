package com.nougatbar.lxp.cart.controller;

import com.nougatbar.lxp.cart.dto.response.CartResponse;
import com.nougatbar.lxp.cart.service.CartService;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carts")
public class CartController {

    private static final Long TEMP_MEMBER_ID = 1L;

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<List<CartResponse>> findCarts() {
        List<CartResponse> response = cartService.findCartsById(TEMP_MEMBER_ID);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CartResponse> addCart(@RequestParam Long courseId) {
        CartResponse response = cartService.addCart(TEMP_MEMBER_ID, courseId);
        return ResponseEntity.created(URI.create("/carts/" + response.courseId())).body(response);
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long courseId) {
        cartService.deleteCart(TEMP_MEMBER_ID, courseId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllCart() {
        cartService.deleteAllCart(TEMP_MEMBER_ID);
        return ResponseEntity.noContent().build();
    }
}
