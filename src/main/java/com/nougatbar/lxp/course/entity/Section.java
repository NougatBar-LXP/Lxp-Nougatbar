package com.nougatbar.lxp.course.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 섹션(Section) 엔티티 클래스
 * <p>
 * <b>Note:</b> 강좌 데이터의 추가/삭제는 구현하지 않고 하드 코딩된 데이터를 이용하는 프로젝트이므로 데이터
 * 변경에 관련된 메소드와 설정들은 구현하지 않았습니다.
 */
@Entity
@Table(name = "sections")
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "section_id")
    private Long sectionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "sequence", nullable = false)
    private Integer sequence = 0;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "section")
    private final List<Lecture> lectures = new ArrayList<>();

    @OneToMany(mappedBy = "section")
    private final List<Mission> missions = new ArrayList<>();

    protected Section() {
    }

    public Section(Course course, String title, Integer sequence) {
        Objects.requireNonNull(course);

        Objects.requireNonNull(title);
        if (title.isBlank()) {
            throw new IllegalArgumentException("title must not be blank");
        } else if (title.length() > 255) {
            throw new IllegalArgumentException("title must not exceed 255 characters");
        }

        Objects.requireNonNull(sequence);
        if (sequence < 0) {
            throw new IllegalArgumentException("sequence must be a non-negative number");
        }

        this.course = course;
        this.title = title;
        this.sequence = sequence;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public Course getCourse() {
        return course;
    }

    public String getTitle() {
        return title;
    }

    public Integer getSequence() {
        return sequence;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public List<Lecture> getLectures() {
        return lectures;
    }

    public List<Mission> getMissions() {
        return missions;
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
