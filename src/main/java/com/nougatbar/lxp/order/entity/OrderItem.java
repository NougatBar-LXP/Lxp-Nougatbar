package com.nougatbar.lxp.order.entity;

import java.util.Objects;

public record OrderItem(Long courseId, int price) {

    public OrderItem {
        Objects.requireNonNull(courseId, "courseId 필수입니다.");
        if (price <= 0) {
            throw new IllegalArgumentException("가격은 양수여야 합니다.");
        }
    }
}
