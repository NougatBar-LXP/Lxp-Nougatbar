package com.nougatbar.lxp.course.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
 * 미션(Mission) 엔티티 클래스
 * <p>
 * <b>Note:</b> 강좌 데이터의 추가/삭제는 구현하지 않고 하드 코딩된 데이터를 이용하는 프로젝트이므로 데이터
 * 변경에 관련된 메소드와 설정들은 구현하지 않았습니다.
 */
@Entity
@Table(name = "missions")
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private Long missionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id")
    private Section section;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "contents", columnDefinition = "TEXT")
    private String contents;

    @Column(name = "sequence", nullable = false)
    private Integer sequence = 0;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    protected Mission() {
    }

    public Mission(Section section, String title, String contents, Integer sequence) {
        Objects.requireNonNull(section);

        Objects.requireNonNull(title);
        if (title.isBlank()) {
            throw new IllegalArgumentException("title must not be blank");
        } else if (title.length() > 255) {
            throw new IllegalArgumentException("title must not exceed 255 characters");
        }

        Objects.requireNonNull(contents);
        if (contents.isBlank()) {
            throw new IllegalArgumentException("contents must not be blank");
        }

        this.section = section;
        this.title = title;
        this.contents = contents;
        this.sequence = sequence;
    }

    public Long getMissionId() {
        return missionId;
    }

    public Section getSection() {
        return section;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
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

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
