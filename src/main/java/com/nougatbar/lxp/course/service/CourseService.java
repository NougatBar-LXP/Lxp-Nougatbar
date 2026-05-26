package com.nougatbar.lxp.course.service;

import com.nougatbar.lxp.course.dto.response.CourseDetailDTO;
import com.nougatbar.lxp.course.dto.response.CourseSummaryDTO;
import com.nougatbar.lxp.course.entity.Course;
import java.util.List;
import java.util.Optional;

public interface CourseService {
    /**
     * 모든 강좌의 요약 정보를 조회합니다. 각 강좌에 대한 상세 정보는 포함되지 않습니다.
     *
     * @return {@link CourseSummaryDTO} 객체들의 불변 리스트
     */
    List<CourseSummaryDTO> listAllCourses();

    List<Course> listAllCourses2();

    /**
     * 주어진 강좌 ID에 해당하는 강좌의 요약 정보를 조회합니다. 상세 정보는 포함되지 않습니다.
     *
     * @param courseId 조회할 강좌의 ID
     * @return 강좌가 존재하는 경우 {@link CourseSummaryDTO} 객체를 감싸는 Optional, 존재하지 않는 경우 빈 Optional
     */
    Optional<CourseSummaryDTO> getCourseSummaryById(Long courseId);

    /**
     * 주어진 강좌 ID에 해당하는 강좌의 상세 정보를 조회합니다.
     *
     * @param courseId 조회할 강좌의 ID
     * @return 강좌가 존재하는 경우 {@link CourseDetailDTO} 객체를 감싸는 Optional, 존재하지 않는 경우 빈 Optional
     */
    Optional<CourseDetailDTO> getCourseDetailById(Long courseId);

    List<CourseSummaryDTO> searchCoursesByTitle(String title);
}
