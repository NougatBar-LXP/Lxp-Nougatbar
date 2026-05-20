package com.nougatbar.lxp.course;

import com.nougatbar.lxp.enrollment.Level;
import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long courseId;

    private String title;

    private String description;

    private Level level; // EASY, NORMAL, HARD

    private String thumbnailUrl;

    protected Course() {
    }

    public Course(Long courseId,
                  String title,
                  String description,
                  Level level,
                  String thumbnailUrl) {
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.level = level;
        this.thumbnailUrl = thumbnailUrl;
    }

    public Long getCourseId() {
        return courseId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Level getLevel() {
        return level;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
}