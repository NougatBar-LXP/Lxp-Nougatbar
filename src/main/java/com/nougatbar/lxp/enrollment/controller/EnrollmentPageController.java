package com.nougatbar.lxp.enrollment.controller;

import com.nougatbar.lxp.enrollment.dto.response.EnrollmentDTO;
import com.nougatbar.lxp.enrollment.service.EnrollmentService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EnrollmentPageController {

    private static final Long TEMP_MEMBER_ID = 1L;

    private final EnrollmentService enrollmentService;

    public EnrollmentPageController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping("/enrollment-ui")
    public String showEnrollments(Model model) {
        List<EnrollmentDTO> enrollments = enrollmentService.findEnrollmentsByMemberId(TEMP_MEMBER_ID);

        model.addAttribute("memberId", TEMP_MEMBER_ID);
        model.addAttribute("enrollments", enrollments);
        model.addAttribute("enrollmentCount", enrollments.size());

        return "enrollment/index";
    }
}
