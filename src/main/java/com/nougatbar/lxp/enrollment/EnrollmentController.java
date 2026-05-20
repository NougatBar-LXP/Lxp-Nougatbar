package com.nougatbar.lxp.enrollment;


import com.nougatbar.lxp.enrollment.dto.ResponseDTO;
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
    public ResponseEntity<List<ResponseDTO>> getMyEnrollments(@PathVariable("memberId") Long memberId) {

        List<ResponseDTO> response = service.findById(memberId);
        return ResponseEntity.ok(response);
    }
}