package com.nougatbar.lxp.order.dto.response;

import com.nougatbar.lxp.course.dto.response.CourseSummaryDTO;
import com.nougatbar.lxp.order.entity.OrderLine;
import java.net.URI;

public record OrderLineResponse(Long orderLineId,
                                Long courseId,
                                String title,
                                String description,
                                URI thumbnailUrl,
                                int price) {

    public static OrderLineResponse from(OrderLine orderLine, CourseSummaryDTO course) {
        return new OrderLineResponse(orderLine.getOrderLineId(),
                orderLine.getCourseId(),
                course == null ? "Course #" + orderLine.getCourseId() : course.title(),
                course == null ? "" : course.description(),
                course == null ? null : course.thumbnailUri(),
                orderLine.getPrice());
    }
}
