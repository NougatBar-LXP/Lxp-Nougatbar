package com.nougatbar.lxp.course.controller;

import com.nougatbar.lxp.common.util.StaticResourceLocator;
import com.nougatbar.lxp.course.application.CourseAppService;
import com.nougatbar.lxp.course.dto.response.CourseSummeryViewModel;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/courses")
public class CourseController {
    private final CourseAppService courseAppService;
    private final StaticResourceLocator staticResourceLocator;

    public CourseController(CourseAppService courseAppService,
                            StaticResourceLocator staticResourceLocator) {
        this.courseAppService = courseAppService;
        this.staticResourceLocator = staticResourceLocator;
    }

    /**
     * 모든 강좌의 요약 정보를 조회하여 강좌 목록 페이지를 렌더링하는 엔드포인트.
     *
     * @param model 뷰 모델 객체, 강좌 요약 데이터를 담아 뷰로 전달하는 데 사용
     * @return 강좌 목록 페이지를 렌더링한 뷰 이름
     */
    @GetMapping
    public String courses(Model model) {
        List<CourseSummeryViewModel> courseSummaries = courseAppService.listCourseSummaries();

        Map<Long, String> thumbnailUrlMap = courseSummaries.stream()
                .collect(Collectors.toMap(CourseSummeryViewModel::courseId,
                        course -> staticResourceLocator.locate(course.courseThumbnailUri())));

        model.addAttribute("courses", courseSummaries);
        model.addAttribute("thumbnailUrlMap", thumbnailUrlMap);

        return "courses/index";
    }
}
