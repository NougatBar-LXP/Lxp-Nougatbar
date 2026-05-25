package com.nougatbar.lxp.course.application;

import com.nougatbar.lxp.course.dto.CourseStatusDTO;
import com.nougatbar.lxp.course.dto.response.CourseDetailDTO;
import com.nougatbar.lxp.course.dto.response.CourseDetailViewModel;
import com.nougatbar.lxp.course.dto.response.CourseSummaryDTO;
import com.nougatbar.lxp.course.dto.response.CourseSummeryViewModel;
import com.nougatbar.lxp.course.service.CourseService;
import com.nougatbar.lxp.member.dto.response.MemberDTO;
import com.nougatbar.lxp.member.service.MemberService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CourseAppService {
    private final MemberService memberService;
    private final CourseService courseService;

    public CourseAppService(MemberService memberService, CourseService courseService) {
        this.memberService = memberService;
        this.courseService = courseService;
    }

    /**
     * 수강 가능한 강좌들에 대한 정보를 조회하는 애플리케이션 서비스 메서드. 시간 상의 이유로 페이지네이션은 생략.
     *
     * @return 강좌 요약 정보 리스트
     */
    public List<CourseSummeryViewModel> listCourseSummaries() {
        List<CourseSummeryViewModel> courseSummaries = new ArrayList<>();

        for (CourseSummaryDTO course : courseService.listAllCourses()) {
            // 미승인 강좌 제외
            if (!CourseStatusDTO.PUBLISHED.equals(course.status())) {
                continue;
            }

            MemberDTO instructor = memberService.getMemberById(course.memberId())
                    .orElseThrow(() -> new IllegalStateException(
                            "강사 정보를 찾을 수 없습니다. ID: " + course.memberId()));

            courseSummaries.add(CourseSummeryViewModel.from(course, instructor));
        }

        return courseSummaries;
    }

    /**
     * 특정 강좌에 대한 상세 정보를 조회하는 애플리케이션 서비스 메서드.
     *
     * @param courseId 조회할 강좌의 ID
     * @return 강좌 상세 정보
     * @throws IllegalStateException 강좌가 존재하지 않거나, 강사 정보를 찾을 수 없는 경우
     */
    public CourseDetailViewModel getCourseDetail(Long courseId) {
        CourseDetailDTO course = courseService.getCourseDetailById(courseId)
                .orElseThrow(() -> new IllegalStateException("강좌 정보를 찾을 수 없습니다. ID: " + courseId));

        MemberDTO instructor = memberService.getMemberById(course.memberId())
                .orElseThrow(() -> new IllegalStateException(
                        "강사 정보를 찾을 수 없습니다. ID: " + course.memberId()));

        return CourseDetailViewModel.from(course, instructor);
    }
}
