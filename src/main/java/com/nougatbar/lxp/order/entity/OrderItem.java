package com.nougatbar.lxp.order.entity;

import java.util.Objects;

public record OrderItem(Long courseId, int price) {

    public OrderItem {
        Objects.requireNonNull(courseId, "courseId is required.");
    }
}
