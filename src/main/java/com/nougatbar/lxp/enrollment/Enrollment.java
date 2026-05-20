package com.nougatbar.lxp.enrollment;

import com.nougatbar.lxp.course.Course;
import com.nougatbar.lxp.member.Member;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "enrollments")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enrollment_id")
    private Long enrollmentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    protected Enrollment() {
    }

    public Enrollment(Boolean status, Course course, Member member) {
        this.status = status;
        this.course = course;
        this.member = member;
    }

    public Long getEnrollmentId() {
        return enrollmentId;
    }

    public Member getMember() {
        return member;
    }

    public Course getCourse() {
        return course;
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