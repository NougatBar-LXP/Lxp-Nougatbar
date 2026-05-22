package com.nougatbar.lxp.course;

import static org.assertj.core.api.Assertions.assertThat;

import com.nougatbar.lxp.course.repository.CourseRepository;
import com.nougatbar.lxp.course.repository.LectureRepository;
import com.nougatbar.lxp.course.repository.MissionRepository;
import com.nougatbar.lxp.course.repository.SectionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * 더미 데이터 로드 검증 테스트
 * <p>
 * data.sql 파일이 정상적으로 로드되고, 예상된 개수의 레코드가 삽입되었는지 확인합니다.
 */
@DataJpaTest
@ActiveProfiles("default")
public class CourseDataLoadTest {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private MissionRepository missionRepository;

    @Test
    public void testDummyDataLoaded() {
        // Arrange & Act
        long courseCount = courseRepository.count();
        long sectionCount = sectionRepository.count();
        long lectureCount = lectureRepository.count();
        long missionCount = missionRepository.count();

        // Assert
        assertThat(courseCount).as("Courses 테이블에 20개의 강좌가 로드되어야 함").isEqualTo(20);

        assertThat(sectionCount).as("Sections 테이블에 68개의 섹션이 로드되어야 함").isEqualTo(68);

        assertThat(lectureCount).as("Lectures 테이블에 90개의 강의가 로드되어야 함").isEqualTo(90);

        assertThat(missionCount).as("Missions 테이블에 20개의 미션이 로드되어야 함").isEqualTo(20);
    }

    @Test
    public void testCourseDataStructure() {
        // Arrange & Act
        var course = courseRepository.findById(1L);

        // Assert
        assertThat(course).as("ID 1번 Course가 존재해야 함").isPresent();

        assertThat(course.get().getTitle()).as("강좌 제목이 'Python 기초 완성'이어야 함")
                .isEqualTo("Python 기초 완성");

        assertThat(course.get().getMemberId()).as("모든 강좌는 member_id=9999를 가져야 함").isEqualTo(9999L);

        assertThat(course.get().getStatus().toString()).as("모든 강좌는 PUBLISHED 상태여야 함")
                .isEqualTo("PUBLISHED");
    }
}
