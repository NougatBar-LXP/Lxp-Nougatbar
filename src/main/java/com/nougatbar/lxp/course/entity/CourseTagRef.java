package com.nougatbar.lxp.course.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * 강좌(Course) - 태그(Rag) 참조 엔티티 클래스
 * <p>
 * <b>Note:</b> 강좌 데이터의 추가/삭제는 구현하지 않고 하드 코딩된 데이터를 이용하는 프로젝트이므로 데이터
 * 변경에 관련된 메소드와 설정들은 구현하지 않았습니다.
 */
@Entity
@Table(name = "course_tag_refs", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"course_id", "tag_id"})})
public class CourseTagRef {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ref_id")
    private Long refId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id", nullable = false)
    private Tag tag;

    protected CourseTagRef() {
    }

    public CourseTagRef(Course course, Tag tag) {
        this.course = course;
        this.tag = tag;
    }

    public Long getRefId() {
        return refId;
    }

    public Course getCourse() {
        return course;
    }

    public Tag getTag() {
        return tag;
    }
}
