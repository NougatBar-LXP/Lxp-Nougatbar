package com.nougatbar.lxp.enrollment.dto.response;

import com.nougatbar.lxp.enrollment.entity.Enrollment;
import java.time.LocalDateTime;

public record EnrollmentDTO(Long enrollmentId,
                            Long memberId,
                            Long courseId,
                            Boolean status,
                            LocalDateTime createdAt) {

    public static EnrollmentDTO from(Enrollment enrollment) {
        return new EnrollmentDTO(enrollment.getEnrollmentId(),
                enrollment.getMemberId(),
                enrollment.getCourseId(),
                enrollment.getStatus(),
                enrollment.getCreatedAt());
    }
}
