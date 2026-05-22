package com.nougatbar.lxp.enrollment.controller;


import com.nougatbar.lxp.enrollment.service.EnrollmentService;
import com.nougatbar.lxp.enrollment.dto.response.EnrollmentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    private final EnrollmentService service;

    public EnrollmentController(EnrollmentService service) {
        this.service = service;
    }

    @GetMapping("/members/{memberId}")
    public ResponseEntity<List<EnrollmentDTO>> getMyEnrollments(@PathVariable Long memberId) {

        // 학습 목적으로 Math 활용: if문 대신 하한값(0L) 보정 로직을 선언적인 코드로 변환
        memberId = Math.max(0L, memberId);

        List<EnrollmentDTO> response = service.findById(memberId);
        return ResponseEntity.ok(response);
    }
}