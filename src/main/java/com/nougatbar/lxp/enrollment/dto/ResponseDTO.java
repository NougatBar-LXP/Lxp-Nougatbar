package com.nougatbar.lxp.enrollment.dto;

import com.nougatbar.lxp.enrollment.entity.Enrollment;
import com.nougatbar.lxp.enrollment.entity.Level;

import java.time.LocalDateTime;

public record ResponseDTO(String title,
                          String description,
                          Level level,
                          Boolean status,
                          String thumbnailUrl,
                          LocalDateTime createdAt) {

    public static ResponseDTO from(Enrollment enrollment) {
        return new ResponseDTO(enrollment.getCourse().getTitle(),
                enrollment.getCourse().getDescription(),
                enrollment.getCourse().getLevel(),
                enrollment.getStatus(),
                enrollment.getCourse().getThumbnailUrl(),
                enrollment.getCreatedAt());
    }
}
