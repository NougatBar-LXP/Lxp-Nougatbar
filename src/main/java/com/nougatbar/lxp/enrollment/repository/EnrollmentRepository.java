package com.nougatbar.lxp.enrollment.repository;

import com.nougatbar.lxp.enrollment.entity.Enrollment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    List<Enrollment> findByMemberIdOrderByCreatedAtDesc(Long memberId);

    boolean existsByMemberIdAndCourseId(Long memberId, Long courseId);
}
