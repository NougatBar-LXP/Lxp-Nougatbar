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

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<List<CartResponse>> findCarts(@RequestParam Long memberId) {
        List<CartResponse> response = cartService.findCartsById(memberId);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CartResponse> addCart(@RequestParam Long memberId,
                                                @RequestParam Long courseId) {
        CartResponse response = cartService.addCart(memberId, courseId);
        return ResponseEntity.created(URI.create("/carts/" + response.courseId())).body(response);
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long courseId,
                                           @RequestParam Long memberId) {
        cartService.deleteCart(memberId, courseId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllCart(@RequestParam Long memberId) {
        cartService.deleteAllCart(memberId);
        return ResponseEntity.noContent().build();
    }
}
