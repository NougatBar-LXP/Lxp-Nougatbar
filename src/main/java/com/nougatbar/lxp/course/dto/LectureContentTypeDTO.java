package com.nougatbar.lxp.course.dto;

import com.nougatbar.lxp.course.entity.LectureContentType;

/**
 * 강의 유형 정보를 담는 열거형 DTO
 */
public enum LectureContentTypeDTO {
    /**
     * 강의가 제공하는 자료의 유형이 PDF 파일인 경우
     */
    PDF,

    /**
     * 강의가 제공하는 자료의 유형이 Video인 경우
     */
    VIDEO;

    public static LectureContentTypeDTO from(LectureContentType lectureContentType) {
        return switch (lectureContentType) {
            case PDF -> PDF;
            case VIDEO -> VIDEO;
        };
    }

    public LectureContentType toEntity() {
        return switch (this) {
            case PDF -> LectureContentType.PDF;
            case VIDEO -> LectureContentType.VIDEO;
        };
    }
}
