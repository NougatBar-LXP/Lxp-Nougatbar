package com.nougatbar.lxp.order.dto.response;

import com.nougatbar.lxp.course.service.CourseService;
import com.nougatbar.lxp.order.entity.Order;
import com.nougatbar.lxp.order.entity.OrderStatus;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(Long orderId,
                            Long memberId,
                            Long total,
                            OrderStatus status,
                            LocalDateTime createdAt,
                            int orderLineCount,
                            List<OrderLineResponse> orderLines) {

    public static OrderResponse from(Order order, CourseService courseService) {
        return new OrderResponse(order.getOrderId(),
                order.getMemberId(),
                order.getTotal(),
                order.getStatus(),
                order.getCreatedAt(),
                order.getOrderLines().size(),
                order.getOrderLines()
                        .stream()
                        .map(orderLine -> OrderLineResponse.from(
                                orderLine,
                                courseService.getCourseSummaryById(orderLine.getCourseId()).orElse(null)
                        ))
                        .toList());
    }
}
