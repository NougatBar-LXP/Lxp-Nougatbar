package com.nougatbar.lxp.cart.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "carts", uniqueConstraints = {
        @jakarta.persistence.UniqueConstraint(columnNames = {"course_id", "member_id"})})
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long cartId;

    @Column(name = "course_id", nullable = false)
    private Long courseId;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    protected Cart() {
    }

    public Cart(Long courseId, Long memberId) {
        this.courseId = courseId;
        this.memberId = memberId;
    }

    public Long getCartId() {
        return cartId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public Long getMemberId() {
        return memberId;
    }

    @Override
    public String toString() {
        return "Cart{" + "cartId=" + cartId + ", courseId=" + courseId + ", memberId=" + memberId
                + '}';
    }
}
