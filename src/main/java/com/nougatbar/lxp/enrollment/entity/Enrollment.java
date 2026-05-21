package com.nougatbar.lxp.enrollment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "enrollments")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enrollment_id")
    private Long enrollmentId;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    protected Enrollment() {
    }

    public Enrollment(Boolean status, Long courseId, Long memberId) {
        this.status = (status != null) ? status : false;
        this.courseId = Objects.requireNonNull(courseId, "강좌 정보는 필수 입니다");
        this.memberId = Objects.requireNonNull(memberId, "회원 정보는 필수 입니다");
    }

    public Long getEnrollmentId() {
        return enrollmentId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public Boolean getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @PrePersist
    void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}