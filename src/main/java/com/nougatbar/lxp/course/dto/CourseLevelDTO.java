package com.nougatbar.lxp.course.dto;

import com.nougatbar.lxp.course.entity.CourseLevel;
import java.util.Objects;

/**
 * 강좌 난이도 정보를 담는 열거형 DTO
 */
public enum CourseLevelDTO {
    /**
     * 초급 수준의 강좌
     */
    EASY,

    /**
     * 중급 수준의 강좌
     */
    NORMAL,

    /**
     * 고급 수준의 강좌
     */
    HARD;

    public static CourseLevelDTO from(CourseLevel courseLevel) {
        return switch (Objects.requireNonNull(courseLevel)) {
            case EASY -> EASY;
            case NORMAL -> NORMAL;
            case HARD -> HARD;
        };
    }

    public CourseLevel toEntity() {
        return switch (this) {
            case EASY -> CourseLevel.EASY;
            case NORMAL -> CourseLevel.NORMAL;
            case HARD -> CourseLevel.HARD;
        };
    }
}
