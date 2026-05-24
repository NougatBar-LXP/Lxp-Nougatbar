package com.nougatbar.lxp.enrollment.service;

import com.nougatbar.lxp.cart.dto.response.CartResponse;
import com.nougatbar.lxp.enrollment.dto.response.EnrollmentDTO;
import com.nougatbar.lxp.enrollment.entity.Enrollment;
import com.nougatbar.lxp.enrollment.repository.EnrollmentRepository;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnrollmentService {

    private final EnrollmentRepository repository;

    public EnrollmentService(EnrollmentRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<EnrollmentDTO> findEnrollmentsByMemberId(Long memberId) {
        if (memberId == null) {
            throw new IllegalArgumentException("memberId is required.");
        }

        List<Enrollment> enrollments = repository.findByMemberIdOrderByCreatedAtDesc(memberId);

        if (enrollments.isEmpty()) {
            return Collections.emptyList();
        }

        return enrollments.stream().map(EnrollmentDTO::from).toList();
    }

    @Transactional
    public void createEnrollment(Long memberId, List<CartResponse> carts) {
        if (memberId == null) {
            throw new IllegalArgumentException("memberId is required.");
        }

        if (carts == null || carts.isEmpty()) {
            throw new IllegalArgumentException("carts must not be empty.");
        }

        for (CartResponse cart : carts) {
            if (!repository.existsByMemberIdAndCourseId(memberId, cart.courseId())) {
                repository.save(Enrollment.create(memberId, cart.courseId()));
            }
        }
    }
}
