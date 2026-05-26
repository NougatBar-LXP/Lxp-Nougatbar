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
            throw new IllegalArgumentException("memberId는 필수입니다.");
        }

        if(memberId == null || memberId <= 0L) {
            throw new IllegalArgumentException("유효하지 않은 회원");
        }

        List<Enrollment> enrollments =
                repository.findByMemberIdOrderByCreatedAtDesc(memberId);

        if (enrollments.isEmpty()) {
            return Collections.emptyList();
        }

        return enrollments.stream().map(EnrollmentDTO::from).toList();
    }

    @Transactional
    public void createEnrollment(Long memberId, List<CartResponse> carts) {
        if (memberId == null) {
            throw new IllegalArgumentException("memberId는 필수입니다.");
        }

        if (carts == null || carts.isEmpty()) {
            throw new IllegalArgumentException("carts는 비어있을 수 없습니다.");
        }

        for (CartResponse cart : carts) {
            if (cart == null) {
                throw new IllegalArgumentException("cart는 null일 수 없습니다.");
            }

            Long courseId = cart.courseId();
            if (courseId == null) {
                throw new IllegalArgumentException("courseId는 필수입니다.");
            }

            if (!repository.existsByMemberIdAndCourseId(memberId, courseId)) {
                repository.save(Enrollment.create(memberId, courseId));
            }
        }
    }
}
