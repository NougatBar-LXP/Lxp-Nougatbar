package com.nougatbar.lxp.enrollment.service;

import com.nougatbar.lxp.enrollment.dto.response.EnrollmentDTO;
import com.nougatbar.lxp.enrollment.entity.Enrollment;
import com.nougatbar.lxp.enrollment.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class EnrollmentService {

    private final EnrollmentRepository repository;

    public EnrollmentService(EnrollmentRepository repository) {
        this.repository = repository;
    }

    public List<EnrollmentDTO> findById(Long memberId) {

        List<Enrollment> enrollments =
                repository.findByMember_MemberIdOrderByCreatedAtDesc(memberId);

        if (enrollments.isEmpty()) {
            return Collections.emptyList();
        }

        return enrollments.stream().map(EnrollmentDTO::from).toList();
    }
}