package com.nougatbar.lxp.course.repository;

import com.nougatbar.lxp.course.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {
}
