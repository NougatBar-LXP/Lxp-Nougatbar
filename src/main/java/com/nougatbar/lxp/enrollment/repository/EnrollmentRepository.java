package com.nougatbar.lxp.enrollment.repository;

import com.nougatbar.lxp.enrollment.entity.Enrollment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    @EntityGraph(attributePaths = {"course"})
    List<Enrollment> findByMember_MemberIdOrderByCreatedAtDesc(Long memberId);
}
