package com.nougatbar.lxp.course.dto;

import com.nougatbar.lxp.course.entity.CourseStatus;
import java.util.Objects;

/**
 * 강좌 상태 정보를 담는 열거형 DTO
 */
public enum CourseStatusDTO {
    /**
     * 강좌가 초안 상태인 경우
     */
    DRAFT,

    /**
     * 강좌가 공개 상태인 경우
     */
    PUBLISHED,

    /**
     * 강좌가 아카이브 상태인 경우
     */
    ARCHIVED;

    public static CourseStatusDTO from(CourseStatus courseStatus) {
        return switch (Objects.requireNonNull(courseStatus)) {
            case DRAFT -> DRAFT;
            case PUBLISHED -> PUBLISHED;
            case ARCHIVED -> ARCHIVED;
        };
    }

    public CourseStatus toEntity() {
        return switch (this) {
            case DRAFT -> CourseStatus.DRAFT;
            case PUBLISHED -> CourseStatus.PUBLISHED;
            case ARCHIVED -> CourseStatus.ARCHIVED;
        };
    }
}
