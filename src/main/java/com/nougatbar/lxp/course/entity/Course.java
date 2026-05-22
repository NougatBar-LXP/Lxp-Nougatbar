package com.nougatbar.lxp.course.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 강좌(Course) 엔티티 클래스
 * <p>
 * <b>Note:</b> 강좌 데이터의 추가/삭제는 구현하지 않고 하드 코딩된 데이터를 이용하는 프로젝트이므로 데이터
 * 변경에 관련된 메소드와 설정들은 구현하지 않았습니다.
 */
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "price", nullable = false)
    private Integer price = 0;

    @Enumerated(EnumType.STRING)
    @Column(name = "level", nullable = false)
    private CourseLevel level;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private CourseStatus status = CourseStatus.DRAFT;

    @Column(name = "thumbnail_url", nullable = false)
    private String thumbnailUrl;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "approved_at")
    private LocalDateTime approvedAt;

    @OneToMany(mappedBy = "course")
    private final List<Section> sections = new ArrayList<>();

    @OneToMany(mappedBy = "course")
    private final List<CourseTagRef> tagRefs = new ArrayList<>();

    protected Course() {
    }

    public Course(Long memberId,
                  String title,
                  String description,
                  Integer price,
                  CourseLevel level,
                  String thumbnailUrl) {
        Objects.requireNonNull(memberId);
        if (memberId <= 0) {
            throw new IllegalArgumentException("memberId must be a positive number");
        }

        Objects.requireNonNull(title);
        if (title.isBlank()) {
            throw new IllegalArgumentException("title must not be blank");
        } else if (title.length() > 255) {
            throw new IllegalArgumentException("title must not exceed 255 characters");
        }

        Objects.requireNonNull(description);
        if (description.isBlank()) {
            throw new IllegalArgumentException("description must not be blank");
        }

        Objects.requireNonNull(price);
        if (price < 0) {
            throw new IllegalArgumentException("price must be a non-negative number");
        }

        Objects.requireNonNull(level);

        Objects.requireNonNull(thumbnailUrl);
        if (thumbnailUrl.isBlank()) {
            throw new IllegalArgumentException("thumbnailUrl must not be blank");
        } else if (thumbnailUrl.length() > 255) {
            throw new IllegalArgumentException("thumbnailUrl must not exceed 255 characters");
        }

        this.memberId = memberId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.level = level;
        this.thumbnailUrl = thumbnailUrl;
    }

    public Long getCourseId() {
        return courseId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPrice() {
        return price;
    }

    public CourseLevel getLevel() {
        return level;
    }

    public CourseStatus getStatus() {
        return status;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public LocalDateTime getApprovedAt() {
        return approvedAt;
    }

    public List<Section> getSections() {
        return sections;
    }

    public List<CourseTagRef> getTagRefs() {
        return tagRefs;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
