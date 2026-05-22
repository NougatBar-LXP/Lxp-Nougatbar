package com.nougatbar.lxp.course.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 강의(Lecture) 엔티티 클래스
 * <p>
 * <b>Note:</b> 강좌 데이터의 추가/삭제는 구현하지 않고 하드 코딩된 데이터를 이용하는 프로젝트이므로 데이터
 * 변경에 관련된 메소드와 설정들은 구현하지 않았습니다.
 */
@Entity
@Table(name = "lectures")
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_id")
    private Long lectureId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "section_id", nullable = false)
    private Section section;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private LectureContentType type;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "sequence", nullable = false)
    private Integer sequence = 0;

    @Column(name = "content_uri", nullable = false)
    private String contentUri;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    protected Lecture() {
    }

    public Lecture(Section section,
                   LectureContentType type,
                   String title,
                   Integer sequence,
                   String contentUri) {
        Objects.requireNonNull(section);

        Objects.requireNonNull(type);

        Objects.requireNonNull(title);
        if (title.isBlank()) {
            throw new IllegalArgumentException("title must not be blank");
        }

        Objects.requireNonNull(sequence);
        if (sequence < 0) {
            throw new IllegalArgumentException("sequence must be non-negative");
        }

        Objects.requireNonNull(contentUri);
        if (contentUri.isBlank()) {
            throw new IllegalArgumentException("contentUri must not be blank");
        }

        this.section = section;
        this.type = type;
        this.title = title;
        this.sequence = sequence;
        this.contentUri = contentUri;
    }

    public Long getLectureId() {
        return lectureId;
    }

    public Section getSection() {
        return section;
    }

    public LectureContentType getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public Integer getSequence() {
        return sequence;
    }

    public String getContentUri() {
        return contentUri;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
