package com.nougatbar.lxp.community.repository;

import com.nougatbar.lxp.community.entity.Community;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<Community, Long> {

    List<Community> findAllByOrderByCreatedAtDesc();

    List<Community> findAllByCourseIdOrderByCreatedAtDesc(Long courseId);
}
