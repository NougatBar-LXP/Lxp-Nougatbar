package com.nougatbar.lxp.course.service.impl;

import com.nougatbar.lxp.course.dto.response.CourseDetailDTO;
import com.nougatbar.lxp.course.dto.response.CourseSummaryDTO;
import com.nougatbar.lxp.course.entity.Course;
import com.nougatbar.lxp.course.repository.CourseRepository;
import com.nougatbar.lxp.course.service.CourseService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<CourseSummaryDTO> listAllCourses() {
        return courseRepository.findAll().stream().map(CourseSummaryDTO::from).toList();
    }

    @Override
    public List<Course> listAllCourses2() {
        return courseRepository.findAll();
    }

    @Override
    public Optional<CourseSummaryDTO> getCourseSummaryById(Long courseId) {
        return courseRepository.findById(courseId).map(CourseSummaryDTO::from);
    }

    @Override
    public Optional<CourseDetailDTO> getCourseDetailById(Long courseId) {
        return courseRepository.findById(courseId).map(CourseDetailDTO::from);
    }
}
