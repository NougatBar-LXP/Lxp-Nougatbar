package com.nougatbar.lxp.order.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "order_lines")
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_line_id")
    private Long orderLineId;

    @Column(name = "course_id", nullable = false)
    private Long courseId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(nullable = false)
    private int price;

    protected OrderLine() {
    }

    OrderLine(Order order, Long courseId, int price) {
        this.order = Objects.requireNonNull(order, "order는 필수입니다.");
        this.courseId = Objects.requireNonNull(courseId, "courseId는 필수입니다.");
        if (price < 0) {
            throw new IllegalArgumentException("가격은 0보다 커야 합니다.");
        }

        this.price = price;
    }

    public Long getOrderLineId() {
        return orderLineId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public Long getOrderId() {
        return order.getOrderId();
    }

    public int getPrice() {
        return price;
    }
}
