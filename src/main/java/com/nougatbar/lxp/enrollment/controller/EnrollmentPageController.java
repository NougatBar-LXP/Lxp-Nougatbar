package com.nougatbar.lxp.enrollment.controller;

import com.nougatbar.lxp.common.util.StaticResourceLocator;
import com.nougatbar.lxp.course.dto.response.CourseSummaryDTO;
import com.nougatbar.lxp.course.service.CourseService;
import com.nougatbar.lxp.enrollment.dto.response.EnrollmentDTO;
import com.nougatbar.lxp.enrollment.service.EnrollmentService;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EnrollmentPageController {

    private static final Long TEMP_MEMBER_ID = 1L;

    private final EnrollmentService enrollmentService;
    private final CourseService courseService;
    private final StaticResourceLocator staticResourceLocator;

    public EnrollmentPageController(EnrollmentService enrollmentService,
                                    CourseService courseService,
                                    StaticResourceLocator staticResourceLocator) {
        this.enrollmentService = enrollmentService;
        this.courseService = courseService;
        this.staticResourceLocator = staticResourceLocator;
    }

    @GetMapping("/enrollment-ui")
    public String showEnrollments(Model model) {
        List<EnrollmentDTO> enrollments = enrollmentService.findEnrollmentsByMemberId(TEMP_MEMBER_ID);
        Map<Long, CourseSummaryDTO> courseMap = enrollments.stream()
                .map(EnrollmentDTO::courseId)
                .distinct()
                .map(courseService::getCourseSummaryById)
                .flatMap(Optional::stream)
                .collect(Collectors.toMap(CourseSummaryDTO::courseId, course -> course));
        Map<Long, String> thumbnailUrlMap = courseMap.values()
                .stream()
                .filter(course -> course.thumbnailUri() != null)
                .collect(Collectors.toMap(CourseSummaryDTO::courseId,
                        course -> staticResourceLocator.locate(course.thumbnailUri())));

        model.addAttribute("memberId", TEMP_MEMBER_ID);
        model.addAttribute("enrollments", enrollments);
        model.addAttribute("enrollmentCount", enrollments.size());
        model.addAttribute("courseMap", courseMap);
        model.addAttribute("thumbnailUrlMap", thumbnailUrlMap);

        return "enrollment/index";
    }
}
