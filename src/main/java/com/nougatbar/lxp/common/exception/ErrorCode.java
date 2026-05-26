package com.nougatbar.lxp.common.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    COMMUNITY_NOT_FOUND(HttpStatus.NOT_FOUND, "Community not found."),
    COMMUNITY_REQUIRED_VALUE_MISSING(
            HttpStatus.BAD_REQUEST,
            "courseId, memberId, type, title, and content are required."
    ),
    COURSE_NOT_FOUND(HttpStatus.NOT_FOUND, "Course not found."),
    COMMUNITY_UPDATE_REQUIRED_VALUE_MISSING(HttpStatus.BAD_REQUEST, "title and content are required."),
    INSTRUCTOR_NOT_FOUND(HttpStatus.NOT_FOUND, "Instructor not found."),
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "Invalid request.");

    private final HttpStatus status;
    private final String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
