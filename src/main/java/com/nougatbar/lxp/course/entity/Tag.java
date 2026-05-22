package com.nougatbar.lxp.course.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * 태그(Rag) 엔티티 클래스
 * <p>
 * <b>Note:</b> 강좌 데이터의 추가/삭제는 구현하지 않고 하드 코딩된 데이터를 이용하는 프로젝트이므로 데이터
 * 변경에 관련된 메소드와 설정들은 구현하지 않았습니다.
 */
@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long tagId;

    @Column(name = "tag_name", nullable = false, unique = true)
    private String tagName;

    protected Tag() {
    }

    public Tag(String tagName) {
        this.tagName = tagName;
    }

    public Long getTagId() {
        return tagId;
    }

    public String getTagName() {
        return tagName;
    }
}
