package com.nougatbar.lxp.enrollment.dto;

import com.nougatbar.lxp.enrollment.Level;

import java.time.LocalDateTime;

public record ResponseDTO(String title,
                          String description,
                          Level level,
                          Boolean status,
                          String thumbnailUrl,
                          LocalDateTime createdAt) {
}
