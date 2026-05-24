package com.nougatbar.lxp.order.service;

import com.nougatbar.lxp.cart.dto.response.CartResponse;
import com.nougatbar.lxp.cart.service.CartService;
import com.nougatbar.lxp.course.service.CourseService;
import com.nougatbar.lxp.enrollment.service.EnrollmentService;
import com.nougatbar.lxp.member.service.MemberService;
import com.nougatbar.lxp.order.dto.response.OrderResponse;
import com.nougatbar.lxp.order.entity.Order;
import com.nougatbar.lxp.order.repository.OrderRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class OrderService {

    private final MemberService memberService;
    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final CourseService courseService;
    private final EnrollmentService enrollmentService;

    public OrderService(MemberService memberService,
                        CartService cartService,
                        OrderRepository orderRepository,
                        CourseService courseService,
                        EnrollmentService enrollmentService) {

        this.memberService = memberService;
        this.orderRepository = orderRepository;
        this.cartService = cartService;
        this.courseService = courseService;
        this.enrollmentService = enrollmentService;
    }

    @Transactional
    public OrderResponse createOrder(Long memberId, List<CartResponse> carts) {
        if (memberId == null) {
            throw new IllegalArgumentException("memberId는 필수입니다");
        }

        memberService.getMemberById(memberId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "멤버를 찾을 수 없습니다. memberId=" + memberId));

        if (carts == null || carts.isEmpty()) {
            throw new IllegalArgumentException("카트 안에 상품이 존재하지 않습니다.");
        }

        Order order = orderRepository.save(Order.create(memberId, carts));
        enrollmentService.createEnrollment(memberId, carts);
        cartService.deleteAllCart(memberId);

        return OrderResponse.from(order, courseService);
    }

    @Transactional(readOnly = true)
    public OrderResponse getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Order를 찾을 수 없습니다."));
        return OrderResponse.from(order, courseService);
    }

    @Transactional(readOnly = true)
    public List<OrderResponse> getOrders(Long memberId) {
        if (memberId == null) {
            throw new IllegalArgumentException("memberId가 존재하지 않습니다");
        }

        return orderRepository.findByMemberIdOrderByCreatedAtDesc(memberId)
                .stream()
                .map(order -> OrderResponse.from(order, courseService))
                .toList();
    }
}
