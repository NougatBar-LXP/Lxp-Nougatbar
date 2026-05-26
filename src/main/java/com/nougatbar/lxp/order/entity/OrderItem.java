package com.nougatbar.lxp.order.entity;

import java.util.Objects;

public record OrderItem(Long courseId, int price) {

    public OrderItem {
        Objects.requireNonNull(courseId, "courseId는 필수입니다.");
        if (price < 0) {
            throw new IllegalArgumentException("가격은 0 이상이여야 합니다.");
        }
    }
}
