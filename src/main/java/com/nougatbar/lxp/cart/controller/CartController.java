package com.nougatbar.lxp.cart.controller;

import com.nougatbar.lxp.cart.dto.response.CartDTO;
import com.nougatbar.lxp.cart.dto.response.CartResponse;
import com.nougatbar.lxp.cart.service.CartService;
import com.nougatbar.lxp.common.util.StaticResourceLocator;
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
    private final StaticResourceLocator staticResourceLocator;

    public CartController(CartService cartService, StaticResourceLocator staticResourceLocator) {
        this.cartService = cartService;
        this.staticResourceLocator = staticResourceLocator;
    }

    @GetMapping
    public ResponseEntity<List<CartResponse>> findCarts() {
        List<CartDTO> cartDTOs = cartService.findCartsById(TEMP_MEMBER_ID);
        List<CartResponse> response = cartDTOs.stream()
                .map(cart -> CartResponse.from(cart, staticResourceLocator::locate))
                .toList();

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CartResponse> addCart(@RequestParam Long courseId) {
        CartDTO dto = cartService.addCart(TEMP_MEMBER_ID, courseId);
        CartResponse response = CartResponse.from(dto, staticResourceLocator::locate);
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
