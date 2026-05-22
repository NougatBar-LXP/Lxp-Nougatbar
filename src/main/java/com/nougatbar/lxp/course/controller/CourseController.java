package com.nougatbar.lxp.course.controller;

import com.nougatbar.lxp.course.dto.response.CourseDetailDTO;
import com.nougatbar.lxp.course.dto.response.CourseSummaryDTO;
import com.nougatbar.lxp.course.service.CourseService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Course 정보 조회 테스를 위한 REST API 컨트롤러. 강좌 목록 조회와 강좌 상세 조회 기능을 제공합니다.
 */
@RestController
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<CourseSummaryDTO>> listCourses() {
        return ResponseEntity.ok(courseService.listAllCourses());
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseDetailDTO> getCourseDetail(@PathVariable Long courseId) {
        return courseService.getCourseDetailById(courseId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
