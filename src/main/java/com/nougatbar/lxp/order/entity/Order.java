package com.nougatbar.lxp.order.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private Long total;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderLine> orderLines = new ArrayList<>();

    protected Order() {
    }

    private Order(Long memberId, Long total, OrderStatus status) {
        this.memberId = Objects.requireNonNull(memberId, "memberId는 필수입니다.");
        this.total = Objects.requireNonNull(total, "total는 필수입니다.");
        this.status = Objects.requireNonNull(status, "status는 필수입니다.");
    }

    public static Order create(Long memberId, List<OrderItem> orderItems) {
        Objects.requireNonNull(orderItems, "orderItems는 필수입니다.");
        if (orderItems.isEmpty()) {
            throw new IllegalArgumentException("orderItems는 비어있을 수 없습니다.");
        }
        Order order = new Order(memberId, calculateTotal(orderItems), OrderStatus.PENDING);
        orderItems.forEach(item -> order.addOrderLine(item.courseId(), item.price()));
        return order;
    }

    private static Long calculateTotal(List<OrderItem> orderItems) {
        return orderItems.stream().mapToLong(OrderItem::price).sum();
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public Long getTotal() {
        return total;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<OrderLine> getOrderLines() {
        return Collections.unmodifiableList(orderLines);
    }

    @PrePersist
    void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    private void addOrderLine(Long courseId, int price) {
        this.orderLines.add(new OrderLine(this, courseId, price));
    }
}
