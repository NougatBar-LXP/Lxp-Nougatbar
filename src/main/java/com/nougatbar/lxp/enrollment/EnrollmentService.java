package com.nougatbar.lxp.enrollment;

import com.nougatbar.lxp.enrollment.dto.ResponseDTO;
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

    public List<ResponseDTO> findById(Long memberId) {

        List<Enrollment> enrollments = repository.findByMemberIdOrderByCreatedAtDesc(memberId);

        if (enrollments.isEmpty()) {
            return Collections.emptyList();
        }

        return enrollments.stream()
                .map(enrollment -> new ResponseDTO(enrollment.getCourse().getTitle(),
                        enrollment.getCourse().getDescription(),
                        enrollment.getCourse().getLevel(),
                        enrollment.getStatus(),
                        enrollment.getCourse().getThumbnailUrl(),
                        enrollment.getCreatedAt()))
                .toList();
    }
}